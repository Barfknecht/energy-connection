package com.example.bluetoothdevicebattery.Mappers;

import android.bluetooth.BluetoothDevice;

import com.example.bluetoothdevicebattery.Models.DeviceModel;

import java.util.ArrayList;
import java.util.List;

public final class DeviceModelMapper {

    private static DeviceModel mapBluetoothDeviceToDeviceModel(BluetoothDevice bluetoothDevice) {
        return new DeviceModel(bluetoothDevice.getType(), bluetoothDevice.getName(), bluetoothDevice.getBondState(), 1, bluetoothDevice.getAddress());
    }

    public static List<DeviceModel> mapDeviceModels(List<BluetoothDevice> bluetoothDevices) {
        List<DeviceModel> deviceModels = new ArrayList<>();
        for (BluetoothDevice bluetoothDevice : bluetoothDevices
        ) {
            deviceModels.add(mapBluetoothDeviceToDeviceModel(bluetoothDevice));
        }

        return deviceModels;
    }
}
