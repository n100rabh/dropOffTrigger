package com.engage.utils;

import com.engage.cache.RedisDataStore;
import com.engage.entity.Notification;
import com.engage.entity.SdkData;
import com.engage.entity.SdkPayload;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sender {

  private static ObjectMapper mapper = new ObjectMapper();
  private String serverUrl;
  private String authHeader;

  public Sender() {
    this.serverUrl = "http://android.googleapis.com/gcm/send";
    this.authHeader = "key=";// AIzaSyC5xX4s-vX5doKXjEYJnpGDMU-57f8CL0c";
  }

  public void send(String channel) {
    String request = createSdkPayload(channel);
    ClientResponse response =
        WebUtils.INSTANCE.doCall(serverUrl, HttpMethod.POST, request, authHeader);
    System.out.println(response.getStatus());
    System.out.println(response);
  }

  private String getDataFromRedis(String channel) {
    StringTokenizer st = new StringTokenizer(channel, ":");
    st.nextElement();
    String key = (String) st.nextElement();
    System.out.println(key);
    String data = new RedisDataStore("").getString(key + "_data");
    System.out.println(data);
    return data;
  }

  private Notification getNotification(String data) {
    Notification notification = null;

    try {
      notification = mapper.readValue(data, Notification.class);
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return notification;
  }

  private String createSdkPayload(String channel) {
    String data = getDataFromRedis(channel);

    Notification notification = getNotification(data);
    authHeader += notification.getApi_server_key();

    SdkData sdkData = new SdkData("Get 'em before they’re gone", notification.getId().toString(),
        "vizury", notification.getDeeplink(), notification.getImageUrl(), "1234",
        notification.getId(), notification.getMessage(), notification.getTitle());

    // String regId =
    // "e2NvUA3FLJk:APA91bFBtVkxhjna9uw3nY0FipV4J47yHSVdjOLz4JO66cY0Y1Ah8uUQCl5DM9Gt8stGTN5e2hcEYobNO6esmWeMnF3zCLAqrv0Q91USIQTXcjqSsfg8GMHmsIocsUOID4aIVVVReWws";
    List<String> regIds = new ArrayList<String>();
    regIds.add(notification.getGcmId());
    SdkPayload sdkPayload = new SdkPayload(sdkData, 172800l, regIds, false);

    String payloadJson = "";
    try {
      payloadJson = mapper.writeValueAsString(sdkPayload);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    System.out.println(payloadJson);
    return payloadJson;
  }
}
