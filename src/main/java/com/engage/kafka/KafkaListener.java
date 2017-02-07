package com.engage.kafka;

import com.engage.cache.RedisSubscriber;
import com.engage.manager.CampaignDataManager;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaListener {
  private final ConsumerConnector consumer;
  private final String topic;
  private ExecutorService executor;

  public KafkaListener(String a_zookeeper, String a_groupId, String a_topic, String consumerId) {
    consumer = kafka.consumer.Consumer
        .createJavaConsumerConnector(createConsumerConfig(a_zookeeper, a_groupId, consumerId));
    this.topic = a_topic;
    run(3);
    System.out.println("finished initializing kafka");
  }

  public void shutdown() {
    if (consumer != null)
      consumer.shutdown();
    if (executor != null)
      executor.shutdown();
    try {
      if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
        System.out
            .println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
      }
    } catch (InterruptedException e) {
      System.out.println("Interrupted during shutdown, exiting uncleanly");
    }
  }

  public void run(int a_numThreads) {
    Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
    topicCountMap.put(topic, new Integer(a_numThreads));
    Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
        consumer.createMessageStreams(topicCountMap);
    List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

    // now launch all the threads
    //
    executor = Executors.newFixedThreadPool(20);

    // now create an object to consume the messages
    //
    int threadNumber = 0;
    for (final KafkaStream stream : streams) {
//      executor.submit(new Profiler(stream, threadNumber));
//      executor.submit(new Notifier(stream, threadNumber));
      System.out.println("Initializing Profiler with thread " + threadNumber);
      new Profiler(stream, threadNumber);
      // System.out.println("Inimtliaing NOtifier with threadNumber " +
      // threadNumber);
      // new Notifier(stream, threadNumber);
      threadNumber++;
    }
    System.out.println("run returned");
  }

  private static ConsumerConfig createConsumerConfig(String a_zookeeper, String a_groupId,
      String consumerId) {
    Properties props = new Properties();
    props.put("zookeeper.connect", a_zookeeper);
    props.put("group.id", a_groupId);
    props.put("consumer.id", consumerId);
    props.put("zookeeper.session.timeout.ms", "10000");
    // props.put("zookeeper.sync.time.ms", "200");
    // props.put("auto.commit.interval.ms", "1000");

    return new ConsumerConfig(props);
  }

  public static void main(String[] args) {
    String zooKeeper = "75.126.75.197:2181,75.126.75.197:2182,75.126.75.197:2183";
    String groupId = "BootCamp1";
    String consumerName = "bootcamp_001";
    String topic = "analyze";
    int threads = 3;

    ClassPathXmlApplicationContext apContext =
        new ClassPathXmlApplicationContext("springContext.xml");
    CampaignDataManager campaignDataManager =
        (CampaignDataManager) apContext.getBean("campaignDataManager");
    campaignDataManager.loadMaps();
    KafkaListener kafkaListener = new KafkaListener(zooKeeper, groupId, topic, consumerName);
//    kafkaListener.run(threads);
    RedisSubscriber redisSubscriber = new RedisSubscriber();

    // Wait forever...
    while (true) {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        kafkaListener.shutdown();
        System.out.println("Main thread interrupted!!");
      }
    }
  }
}
