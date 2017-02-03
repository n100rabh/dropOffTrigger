package com.engage.manager;

import com.engage.entity.CampaignData;
import com.engage.entity.CampaignSetting;
import com.engage.entity.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CampaignDataManager {
  Map<String, List<String>> campaignNotificationsMap;
  Map<String, CampaignSetting> campaignSettingsMap;
  Map<Integer, Notification> notificationsMap;

  public CampaignDataManager() {
    this.campaignNotificationsMap = new ConcurrentHashMap<String, List<String>>();
    this.campaignSettingsMap = new ConcurrentHashMap<String, CampaignSetting>();
    this.notificationsMap = new ConcurrentHashMap<Integer, Notification>();
  }

  public void createMaps() {
    List<CampaignData> campaignDatas = getCampaignData();
    for (CampaignData campaignData : campaignDatas) {
      String campaignId = campaignData.getCampaignId();
      CampaignSetting campaignSetting = new CampaignSetting(campaignId,
          campaignData.getMessage_per_day(), campaignData.getApi_server_key());
      campaignSettingsMap.putIfAbsent(campaignId, campaignSetting);
    }
  }

  public List<CampaignData> getCampaignData() {
    List<CampaignData> campaignDatas = new ArrayList<CampaignData>();
    return campaignDatas;
  }
}
