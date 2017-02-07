package com.engage.entity;

import java.util.List;

public class SdkPayload {

  private SdkData data;
  private Long time_to_live;
  private List<String> registration_ids;
  private Boolean dry_run;

  public SdkPayload() {
  }

  public SdkPayload(SdkData data, Long time_to_live, List<String> registration_ids,
    Boolean dry_run) {
    this.data = data;
    this.time_to_live = time_to_live;
    this.registration_ids = registration_ids;
    this.dry_run = dry_run;
  }

  public SdkData getData() {
    return data;
  }

  public void setData(SdkData data) {
    this.data = data;
  }

  public Long getTime_to_live() {
    return time_to_live;
  }

  public void setTime_to_live(Long time_to_live) {
    this.time_to_live = time_to_live;
  }

  public List<String> getRegistration_ids() {
    return registration_ids;
  }

  public void setRegistration_ids(List<String> registration_ids) {
    this.registration_ids = registration_ids;
  }

  public Boolean getDry_run() {
    return dry_run;
  }

  public void setDry_run(Boolean dry_run) {
    this.dry_run = dry_run;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SdkPayload [data=").append(data).append(", time_to_live=")
        .append(time_to_live).append(", registration_ids=").append(registration_ids)
        .append(", dry_run=").append(dry_run).append("]");
    return builder.toString();
  }
}
