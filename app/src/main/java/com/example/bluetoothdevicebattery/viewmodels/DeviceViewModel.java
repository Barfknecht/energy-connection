package com.example.bluetoothdevicebattery.viewmodels;

public class DeviceViewModel {


    public DeviceViewModel(String deviceName, int connectionState, int deviceType) {
        this.deviceName = deviceName;
        this.connectionState = connectionState;
        this.deviceType = deviceType;
    }

    public String deviceName;
    public int connectionState;
    public int deviceType;
}
