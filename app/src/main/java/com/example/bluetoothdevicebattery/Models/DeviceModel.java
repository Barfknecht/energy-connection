package com.example.bluetoothdevicebattery.Models;

import androidx.annotation.Nullable;

public class DeviceModel {

    public int deviceType;
    public String deviceName;
    public int batteryLevel;
    public int connectionState;
    public String address;

    public DeviceModel(int deviceType, String deviceName, int connectionState, int batteryLevel, String address) {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.connectionState = connectionState;
        this.batteryLevel = batteryLevel;
        this.address = address;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else {
            DeviceModel method = (DeviceModel) object;
            if (this.address.equals(method.address)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 7 * hash + this.address.hashCode();
        return hash;
    }
}
