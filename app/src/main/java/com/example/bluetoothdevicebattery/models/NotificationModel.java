package com.example.bluetoothdevicebattery.models;

import android.graphics.drawable.Icon;

public class NotificationModel {

    public NotificationModel(String notificationTitle, String contentText, int batteryLevel, Icon smallIcon, Icon bigIcon) {
        this.notificationTitle = notificationTitle;
        this.contentText = contentText;
        this.batteryLevel = batteryLevel;
        this.smallIcon = smallIcon;
        this.bigIcon = bigIcon;
    }

    public NotificationModel(String notificationTitle, String contentText, int batteryLevel) {
        this.notificationTitle = notificationTitle;
        this.contentText = contentText;
        this.batteryLevel = batteryLevel;
    }

    public String notificationTitle;
    public String contentText;
    public int batteryLevel;

    public Icon smallIcon;
    public Icon bigIcon;

}
