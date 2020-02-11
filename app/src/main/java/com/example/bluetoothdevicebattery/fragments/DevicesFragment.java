package com.example.bluetoothdevicebattery.fragments;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluetoothdevicebattery.adapters.DevicesViewModelAdapter;
import com.example.bluetoothdevicebattery.listeners.BluetoothDeviceListener;
import com.example.bluetoothdevicebattery.mappers.DeviceViewModelMapper;
import com.example.bluetoothdevicebattery.models.DeviceModel;
import com.example.bluetoothdevicebattery.R;
import com.example.bluetoothdevicebattery.managers.BluetoothDeviceManager;
import com.example.bluetoothdevicebattery.viewmodels.DeviceViewModel;

import java.util.ArrayList;
import java.util.List;


public class DevicesFragment extends Fragment {

    private List<DeviceViewModel> consolidatedDeviceViewModels = new ArrayList<>();
    private List<DeviceModel> pairedDeviceModels = new ArrayList<>();

    private BluetoothDeviceListener bluetoothDeviceListener;
    private BluetoothDeviceManager bluetoothDeviceManager;
    private DevicesViewModelAdapter devicesViewModelAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothDeviceListener = new BluetoothDeviceListener();
        bluetoothDeviceListener.setBluetoothDeviceListener(listener);
        bluetoothDeviceManager = new BluetoothDeviceManager(getActivity(), bluetoothDeviceListener);
        devicesViewModelAdapter = new DevicesViewModelAdapter(consolidatedDeviceViewModels);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View devicesFragment = inflater.inflate(R.layout.devices_fragment, container, false);
        RecyclerView recyclerView = devicesFragment.findViewById(R.id.bluetooth_devices);
        customiseRecyclerView(recyclerView);

        pairedDeviceModels = bluetoothDeviceManager.determinePairedDevices();
        bluetoothDeviceManager.determineConnectedDevices();

        return devicesFragment;
    }

    private void customiseRecyclerView(RecyclerView recyclerView) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(devicesViewModelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onResume() {
        super.onResume();
        pairedDeviceModels = bluetoothDeviceManager.determinePairedDevices();
        bluetoothDeviceManager.determineConnectedDevices();
    }

    final BluetoothDeviceListener.IBluetoothDeviceListener listener = new BluetoothDeviceListener.IBluetoothDeviceListener() {
        @Override
        public void updateConnectedDevices(List<DeviceModel> connectedBluetoothDevices) {
            determineConsolidatedDeviceViewModels(connectedBluetoothDevices);
            devicesViewModelAdapter.notifyDataSetChanged();
        }
    };

    private void determineConsolidatedDeviceViewModels(List<DeviceModel> connectedBluetoothDevices) {

        List<DeviceModel> consolidatedDeviceModel = new ArrayList<>();
        for (DeviceModel device : pairedDeviceModels
        ) {
            if (connectedBluetoothDevices.contains(device)){
                device.connectionState = BluetoothAdapter.STATE_CONNECTED;
            }
            consolidatedDeviceModel.add(device);
        }
        consolidatedDeviceViewModels.clear();
        consolidatedDeviceViewModels.addAll(DeviceViewModelMapper.mapDeviceViewModels(consolidatedDeviceModel));
    }
}
