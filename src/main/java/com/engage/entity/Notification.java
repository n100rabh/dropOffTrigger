
package com.engage.entity;

public class Notification {
  private Integer id;
  private String type;
  private String enagagementName;
  private String message;
  private String title;
  private Long timebasedTime;
  private Long dropOffInterval;
  private String stage;
  private String deeplink;
  private String imageUrl;
  private String api_server_key;
  private String gcmId;

  public Notification() {
  }

  public Notification(Integer id, String enagagementName, String message, String title,
    Long dropOffInterval, String stage,
    String deeplink, String imageUrl) {
    super();
    this.message = message;
    this.title = title;
    this.dropOffInterval = dropOffInterval;
    this.stage = stage;
    this.deeplink = deeplink;
    this.imageUrl = imageUrl;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEnagagementName() {
    return enagagementName;
  }

  public void setEnagagementName(String enagagementName) {
    this.enagagementName = enagagementName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Long getDropOffInterval() {
    return dropOffInterval;
  }

  public void setDropOffInterval(Long dropOffInterval) {
    this.dropOffInterval = dropOffInterval;
  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getDeeplink() {
    return deeplink;
  }

  public void setDeeplink(String deeplink) {
    this.deeplink = deeplink;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getApi_server_key() {
    return api_server_key;
  }

  public void setApi_server_key(String api_server_key) {
    this.api_server_key = api_server_key;
  }

  public String getGcmId() {
    return gcmId;
  }

  public void setGcmId(String gcmId) {
    this.gcmId = gcmId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Long getTimebasedTime() {
    return timebasedTime;
  }

  public void setTimebasedTime(Long timebasedTime) {
    this.timebasedTime = timebasedTime;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Notification [id=").append(id).append(", enagagementName=")
        .append(enagagementName).append(", message=").append(message).append(", title=")
        .append(title).append(", dropOffInterval=").append(dropOffInterval).append(", stage=")
        .append(stage).append(", deeplink=").append(deeplink).append(", imageUrl=")
        .append(imageUrl).append("]");
    return builder.toString();
  }
}
