package com.example.bluetoothdevicebattery.listeners;

import com.example.bluetoothdevicebattery.models.DeviceModel;

import java.util.List;

public class BluetoothDeviceListener {

    public interface IBluetoothDeviceListener{
        void updateConnectedDevices(List<DeviceModel> connectedBluetoothDevices);
    }

    private IBluetoothDeviceListener bluetoothDeviceListener;

    public BluetoothDeviceListener(){
        this.bluetoothDeviceListener = null;
    }

    public void setBluetoothDeviceListener(IBluetoothDeviceListener bluetoothDeviceListener){
        this.bluetoothDeviceListener = bluetoothDeviceListener;
    }

    public void updateConnectedDevices(List<DeviceModel> bluetoothDevices){
        bluetoothDeviceListener.updateConnectedDevices(bluetoothDevices);
    }

}
