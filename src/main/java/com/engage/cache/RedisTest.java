package com.engage.cache;

public class RedisTest {

  public static void main(String[] args) {
    RedisSubscriber redisSubscriber = new RedisSubscriber();
    while (true) {
      try {
        Thread.sleep(2000l);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
