package com.engage.cache;

import com.engage.utils.Sender;

import redis.clients.jedis.JedisPubSub;

public class KeyEventListener extends JedisPubSub {

  @Override
  public void onMessage(String channel, String message) {
    // TODO Auto-generated method stub
    super.onMessage(channel, message);
  }

  @Override
  public void onPMessage(String pattern, String channel, String message) {
    // TODO Auto-generated method stub
    super.onPMessage(pattern, channel, message);
    System.out.println("pattern: " + pattern + " channel: " + channel + " message:" + message);
    new Sender().send(channel);
  }

  @Override
  public void onPSubscribe(String pattern, int subscribedChannels) {
    // TODO Auto-generated method stub
    super.onPSubscribe(pattern, subscribedChannels);
  }

  @Override
  public void onPUnsubscribe(String pattern, int subscribedChannels) {
    // TODO Auto-generated method stub
    super.onPUnsubscribe(pattern, subscribedChannels);
  }

  @Override
  public void onSubscribe(String channel, int subscribedChannels) {
    // TODO Auto-generated method stub
    super.onSubscribe(channel, subscribedChannels);
  }

  @Override
  public void onUnsubscribe(String channel, int subscribedChannels) {
    // TODO Auto-generated method stub
    super.onUnsubscribe(channel, subscribedChannels);
  }
}
