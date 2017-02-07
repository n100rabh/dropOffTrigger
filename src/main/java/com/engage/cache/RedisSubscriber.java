
package com.engage.cache;

public class RedisSubscriber {

  public RedisSubscriber() {
    System.out.println("connect ho gya");
    RedisDataStore.jedisPool.getResource().psubscribe(new KeyEventListener(), "*");
    System.out.println("chal gya");
  }
}
