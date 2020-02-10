package com.example.bluetoothdevicebattery;

public final class Constants {

    private Constants(){}

    public static int REQUEST_ENABLE_BT = 1;
    public static String CHANNEL_ID = "channel_device_battery_1";
    public static String CHANNEL_NAME = "Device battery";
    public static String CHANNEL_DESCRIPTION = "Device battery notifications";
    public static boolean CHANNEL_VIBRATE = false;
    public static final String ACTION_BATTERY_LEVEL_CHANGED =
            "android.bluetooth.device.action.BATTERY_LEVEL_CHANGED";
    public static final String EXTRA_BATTERY_LEVEL =
            "android.bluetooth.device.extra.BATTERY_LEVEL";
}
