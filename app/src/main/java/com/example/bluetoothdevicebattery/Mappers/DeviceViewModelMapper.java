package com.example.bluetoothdevicebattery.Mappers;

import com.example.bluetoothdevicebattery.Models.DeviceModel;
import com.example.bluetoothdevicebattery.ViewModels.DeviceViewModel;

import java.util.ArrayList;
import java.util.List;

public final class DeviceViewModelMapper {

    private static DeviceViewModel mapDeviceModelToDeviceViewModel(DeviceModel deviceModel) {
        return new DeviceViewModel(deviceModel.deviceName, deviceModel.connectionState, deviceModel.deviceType);
    }

    public static List<DeviceViewModel> mapDeviceViewModels(List<DeviceModel> deviceModels) {
        List<DeviceViewModel> deviceViewModels = new ArrayList<>();
        for (DeviceModel deviceModel : deviceModels
        ) {
            deviceViewModels.add(mapDeviceModelToDeviceViewModel(deviceModel));
        }

        return deviceViewModels;
    }
}
