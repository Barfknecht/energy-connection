package com.example.bluetoothdevicebattery.managers;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.example.bluetoothdevicebattery.Constants;
import com.example.bluetoothdevicebattery.listeners.BluetoothBroadcastListener;
import com.example.bluetoothdevicebattery.listeners.BluetoothDeviceListener;
import com.example.bluetoothdevicebattery.listeners.BluetoothServiceListener;
import com.example.bluetoothdevicebattery.mappers.DeviceModelMapper;
import com.example.bluetoothdevicebattery.models.DeviceModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothDeviceManager {

    final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    final BluetoothBroadcastListener broadcastListener = new BluetoothBroadcastListener();


    private BluetoothServiceListener bluetoothServiceListener;
    private Context context;

    public BluetoothDeviceManager(Context context, BluetoothDeviceListener bluetoothDeviceListener) {
        this.context = context;
        bluetoothServiceListener = new BluetoothServiceListener(bluetoothDeviceListener);
        registerBroadcastFilters();
    }

    public void determineConnectedDevices() {
        if (isBluetoothOn()) {
            setupBluetoothProxyProfile();
        } else
            startEnableBluetoothIntent();
    }

    public List<DeviceModel> determinePairedDevices() {
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        List<BluetoothDevice> pairedDevices = new ArrayList<>();
        pairedDevices.addAll(bondedDevices);
        return DeviceModelMapper.mapDeviceModels(pairedDevices);
    }

    private void setupBluetoothProxyProfile() {
        bluetoothAdapter.getProfileProxy(context, bluetoothServiceListener, BluetoothAdapter.STATE_CONNECTED);
    }

    private boolean isBluetoothOn() {
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    private void startEnableBluetoothIntent() {
        if (context instanceof Activity) {
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            ((Activity) context).startActivityForResult(enableBluetoothIntent, Constants.REQUEST_ENABLE_BT);
        } else {
            Log.e("checkBluetoothStatus", "context should be an instanceof Activity.");
        }
    }

    private void registerBroadcastFilters(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        filter.addAction(Constants.ACTION_BATTERY_LEVEL_CHANGED);
        filter.addAction(Constants.EXTRA_BATTERY_LEVEL);
        context.registerReceiver(broadcastListener, filter);
    }

    final BluetoothDeviceListener.IBluetoothDeviceListener listener = new BluetoothDeviceListener.IBluetoothDeviceListener() {
        @Override
        public void updateConnectedDevices(List<DeviceModel> connectedBluetoothDevices) {
            determineConsolidatedDeviceViewModels(connectedBluetoothDevices);
            devicesViewModelAdapter.notifyDataSetChanged();
        }
    };
}


