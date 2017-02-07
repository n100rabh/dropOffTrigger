
package com.engage.manager;

import com.engage.endpoints.DropoffAPI;
import com.engage.entity.CampaignData;
import com.engage.entity.CampaignSetting;
import com.engage.entity.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CampaignDataManager {
  public static Map<String, List<Integer>> campaignNotificationsMap =
      new ConcurrentHashMap<String, List<Integer>>();
  public static Map<String, CampaignSetting> campaignSettingsMap =
      new ConcurrentHashMap<String, CampaignSetting>();
  public static Map<Integer, Notification> notificationsMap =
      new ConcurrentHashMap<Integer, Notification>();

  public CampaignDataManager() {
    // DropoffAPI dropoffAPI =
    // this.campaignNotificationsMap = new ConcurrentHashMap<String,
    // List<Integer>>();
    // this.campaignSettingsMap = new ConcurrentHashMap<String,
    // CampaignSetting>();
    // this.notificationsMap = new ConcurrentHashMap<Integer, Notification>();
  }

  public void loadMaps() {
    List<CampaignData> campaignDatas = getCampaignData();
    Map<String, List<Integer>> campaignNotificationsMapLocal =
        new ConcurrentHashMap<String, List<Integer>>();
    Map<String, CampaignSetting> campaignSettingsMapLocal =
        new ConcurrentHashMap<String, CampaignSetting>();
    Map<Integer, Notification> notificationsMapLocal =
        new ConcurrentHashMap<Integer, Notification>();
    for (CampaignData campaignData : campaignDatas) {
      String campaignId = campaignData.getCampaignId();
      CampaignSetting campaignSetting = new CampaignSetting(campaignId,
          campaignData.getMessage_per_day(), campaignData.getApi_server_key(),
          campaignData.getSubsequent_push_interval());
      campaignSettingsMapLocal.putIfAbsent(campaignId, campaignSetting);
      List<Integer> notificationIds = new ArrayList<Integer>();
      for (Notification notification : campaignData.getNotifications()) {
        notificationIds.add(notification.getId());
        notificationsMapLocal.putIfAbsent(notification.getId(), notification);
      }
      campaignNotificationsMapLocal.putIfAbsent(campaignId, notificationIds);
    }
    campaignNotificationsMap = campaignNotificationsMapLocal;
    campaignSettingsMap = campaignSettingsMapLocal;
    notificationsMap = notificationsMapLocal;
  }

  public List<CampaignData> getCampaignData() {
    return DropoffAPI.getnotification();
  }
}
