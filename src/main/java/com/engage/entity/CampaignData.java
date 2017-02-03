
package com.engage.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CampaignData {
  private String campaignId;
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
    this.campaignId = values[9];
    getCampaignDataFromDB();
  }

  private void getCampaignDataFromDB() {
    this.message_per_day = 40;
    this.api_server_key = "blah";
    this.subsequent_push_interval = 20l;
    this.notifications = new ArrayList<Notification>();
    for (int i = 0; i < 5; i++) {
      Notification temp = new Notification(1, "engagement_name", "message", "title", 900l, "e400",
          "http://www.koovs.com/koovs-light-weight-pocket-tee_285-80076.html?skuid=327146&utm_source=vizury&utm_medium=push&utm_campaign=Bag+dropoff+-+Andr+-+1608&utm_term=Add+to+Cart%2FBag+-+Dropoffs&utm_content=android&",
          "http://cdn8.vizury.com/images2/get.php?c=VIZVRM4271&f=http%3A%2F%2Fimages.kooves.com%2Fuploads%2Fproducts%2F80076_5c53cc62397b190995f9d718d33fa545_image1_default.jpg&s=3649226315&pad=1&bdw=20&bdc=ffffff");
      this.notifications.add(temp);
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
