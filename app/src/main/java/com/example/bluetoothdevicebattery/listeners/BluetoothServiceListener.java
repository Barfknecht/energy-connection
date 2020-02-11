package com.example.bluetoothdevicebattery.listeners;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;

import com.example.bluetoothdevicebattery.mappers.DeviceModelMapper;
import com.example.bluetoothdevicebattery.models.DeviceModel;

import java.util.List;

public class BluetoothServiceListener implements BluetoothProfile.ServiceListener {

    private BluetoothDeviceListener bluetoothDeviceListener;

    public BluetoothServiceListener(BluetoothDeviceListener bluetoothDeviceListener) {
        this.bluetoothDeviceListener = bluetoothDeviceListener;
    }

    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        List<BluetoothDevice> bluetoothDevices = proxy.getConnectedDevices();

        List<DeviceModel> deviceModels = DeviceModelMapper.mapDeviceModels(bluetoothDevices);
        bluetoothDeviceListener.updateConnectedDevices(deviceModels);
    }

    @Override
    public void onServiceDisconnected(int profile) {

    }

}