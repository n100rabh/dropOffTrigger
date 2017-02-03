package com.engage.entity;

public class CampaignSetting {
  private String campaignId;
  private Integer message_per_day;
  private String api_server_key;

  public CampaignSetting() {
    super();
  }

  public CampaignSetting(String campaignId, Integer message_per_day, String api_server_key) {
    super();
    this.campaignId = campaignId;
    this.message_per_day = message_per_day;
    this.api_server_key = api_server_key;
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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CampaignSetting [campaignId=").append(campaignId).append(", message_per_day=")
        .append(message_per_day).append(", api_server_key=").append(api_server_key).append("]");
    return builder.toString();
  }
}
