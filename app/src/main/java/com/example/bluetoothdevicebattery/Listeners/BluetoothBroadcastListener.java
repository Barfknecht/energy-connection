package com.example.bluetoothdevicebattery.Listeners;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.bluetoothdevicebattery.Constants;

public class BluetoothBroadcastListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            switch (action) {
                case BluetoothDevice.ACTION_ACL_CONNECTED:
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED:
                    break;
                case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                    break;
                case Constants.ACTION_BATTERY_LEVEL_CHANGED:
                    break;
                case Constants.EXTRA_BATTERY_LEVEL:
                    break;
            }
        }

    }
}
