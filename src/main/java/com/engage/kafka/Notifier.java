
package com.engage.kafka;

import com.engage.cache.RedisDataStore;
import com.engage.entity.CampaignData;
import com.engage.entity.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class Notifier implements Runnable {
  private static RedisDataStore redisDataStore = new RedisDataStore("");
  private static ObjectMapper objectMapper = new ObjectMapper();
  private KafkaStream m_stream;
  private int m_threadNumber;

  public Notifier() {

  }
  public Notifier(KafkaStream a_stream, int a_threadNumber) {
    m_threadNumber = a_threadNumber;
    m_stream = a_stream;
    Thread t = new Thread(this, "NOtifier thread " + a_threadNumber);
    t.start();
  }

  public void run() {
    ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
    while (it.hasNext()) {
      try {
      String analyzeData = new String(it.next().message());
      System.out.println("Notifier: Thread " + m_threadNumber + ": " + analyzeData);
      updateRedis(analyzeData);
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    System.out.println("Shutting down Thread: " + m_threadNumber);
  }

  public void updateRedis(String analyzeData) {
    CampaignData campaignData = new CampaignData(analyzeData);
    System.out.println(campaignData);
    Map<String, Integer> keyValueMap = new HashMap<String, Integer>();
    for (Notification notification : campaignData.getNotifications()) {
      String notificationJson = null;
      String key = notification.getId() + "_" + campaignData.getVizId();
      try {
        notificationJson = objectMapper.writeValueAsString(notification);
      } catch (JsonProcessingException e) {
        System.out.println(e.toString());
      }
      System.out.println(key + " : " + notificationJson);
      System.out.println("Engagement Type: " + notification.getType());
      if (notification.getType().equals("TIMEBASED")) {
        Long diff = notification.getTimebasedTime() - System.currentTimeMillis();
        Integer expiry = diff.intValue() / 1000;
        System.out.println("Timebased expiry time: " + expiry);
        keyValueMap.put(key, expiry);
      } else if (notification.getType().equals("DROPOFF")) {
        keyValueMap.put(key, notification.getDropOffInterval().intValue());
      } else {
        keyValueMap.put(key, 0);
      }
      redisDataStore.putData(key + "_data", notificationJson);
    }
    redisDataStore.setStringWithExpiry(keyValueMap);
  }
}
