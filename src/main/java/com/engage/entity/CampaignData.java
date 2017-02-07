
package com.engage.entity;

import com.engage.manager.CampaignDataManager;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CampaignData {
  private String campaignId;
  private String vizId;
  private Integer message_per_day;
  private String api_server_key;
  private Long subsequent_push_interval;
  private List<Notification> notifications;

  public CampaignData() {
  }

  public CampaignData(String analyzeData) {
    parseAnalyzeData(analyzeData);
  }

  public String getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(String campaignId) {
    this.campaignId = campaignId;
  }

  public String getVizId() {
    return vizId;
  }

  public void setVizId(String vizId) {
    this.vizId = vizId;
  }

  public Integer getMessage_per_day() {
    return message_per_day;
  }

  public void setMessage_per_day(Integer message_per_day) {
    this.message_per_day = message_per_day;
  }

  public String getApi_server_key() {
    return api_server_key;
  }

  public void setApi_server_key(String api_server_key) {
    this.api_server_key = api_server_key;
  }

  public Long getSubsequent_push_interval() {
    return subsequent_push_interval;
  }

  public void setSubsequent_push_interval(Long subsequent_push_interval) {
    this.subsequent_push_interval = subsequent_push_interval;
  }

  public List<Notification> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<Notification> notifications) {
    this.notifications = notifications;
  }

  private void parseAnalyzeData(String analyzeData) {
    StringTokenizer st = new StringTokenizer(analyzeData);
    String[] values = new String[100];
    int i = 0;
    while (st.hasMoreElements()) {
      values[i++] = (String) st.nextElement();
    }
    this.vizId = values[3];
    this.campaignId = values[9];
    fillCampaignDataFromMaps(getGcmId(values));
  }

  private String getGcmId(String[] values) {
    String url = values[5];
    String[] pairs = url.split("&");
    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
    for (String pair : pairs) {
      if (StringUtils.isBlank(pair))
        continue;
      int idx = pair.indexOf("=");
      try {
        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
            URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
      } catch (UnsupportedEncodingException e) {
        System.out.println(e.toString());
      }
    }
    return query_pairs.get("gcmid");
  }

  private void fillCampaignDataFromMaps(String gcmId) {
    CampaignSetting campaignSetting = CampaignDataManager.campaignSettingsMap.get(campaignId);
    this.message_per_day = campaignSetting.getMessage_per_day();
    this.api_server_key = campaignSetting.getApi_server_key();
    this.subsequent_push_interval = campaignSetting.getSubsequent_push_interval();
    List<Integer> notificationIds = CampaignDataManager.campaignNotificationsMap.get(campaignId);
    this.notifications = new ArrayList<Notification>();
    for (Integer id : notificationIds) {
      Notification notification = CampaignDataManager.notificationsMap.get(id);
      notification.setApi_server_key(api_server_key);
      notification.setGcmId(gcmId);
      this.notifications.add(notification);
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CampaignData [campaignId=").append(campaignId).append(", message_per_day=")
        .append(message_per_day).append(", api_server_key=").append(api_server_key)
        .append(", subsequent_push_interval=").append(subsequent_push_interval)
        .append(", notifications=").append(notifications).append("]");
    return builder.toString();
  }
}
