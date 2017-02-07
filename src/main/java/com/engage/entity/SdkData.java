package com.engage.entity;

public class SdkData {

  private String gcm_subtext;
  private String bannerid;
  private String push_from;
  private String deeplink;
  private String imageURL;
  private String zoneid;
  private Integer notificationid;
  private String message;
  private String gcm_title;

  public SdkData() {
  }

  public SdkData(String gcm_subtext, String bannerid, String push_from, String deeplink,
    String imageURL, String zoneid, Integer notificationid, String message, String gcm_title) {
    super();
    this.gcm_subtext = gcm_subtext;
    this.bannerid = bannerid;
    this.push_from = push_from;
    this.deeplink = deeplink;
    this.imageURL = imageURL;
    this.zoneid = zoneid;
    this.notificationid = notificationid;
    this.message = message;
    this.gcm_title = gcm_title;
  }

  public String getGcm_subtext() {
    return gcm_subtext;
  }

  public void setGcm_subtext(String gcm_subtext) {
    this.gcm_subtext = gcm_subtext;
  }

  public String getBannerid() {
    return bannerid;
  }

  public void setBannerid(String bannerid) {
    this.bannerid = bannerid;
  }

  public String getPush_from() {
    return push_from;
  }

  public void setPush_from(String push_from) {
    this.push_from = push_from;
  }

  public String getDeeplink() {
    return deeplink;
  }

  public void setDeeplink(String deeplink) {
    this.deeplink = deeplink;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getZoneid() {
    return zoneid;
  }

  public void setZoneid(String zoneid) {
    this.zoneid = zoneid;
  }

  public Integer getNotificationid() {
    return notificationid;
  }

  public void setNotificationid(Integer notificationid) {
    this.notificationid = notificationid;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getGcm_title() {
    return gcm_title;
  }

  public void setGcm_title(String gcm_title) {
    this.gcm_title = gcm_title;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SdkData [gcm_subtext=").append(gcm_subtext).append(", bannerid=")
        .append(bannerid).append(", push_from=").append(push_from).append(", deeplink=")
        .append(deeplink).append(", imageURL=").append(imageURL).append(", zoneid=")
        .append(zoneid).append(", notificationid=").append(notificationid).append(", message=")
        .append(message).append(", gcm_title=").append(gcm_title).append("]");
    return builder.toString();
  }
}
