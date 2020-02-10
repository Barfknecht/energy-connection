package com.example.bluetoothdevicebattery.Adapters;

import android.bluetooth.BluetoothAdapter;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluetoothdevicebattery.R;
import com.example.bluetoothdevicebattery.ViewModels.DeviceViewModel;

import java.util.List;

public class DevicesViewModelAdapter extends RecyclerView.Adapter<DevicesViewModelAdapter.DeviceViewHolder> {

    private List<DeviceViewModel> deviceViewModels;
    public DevicesViewModelAdapter(List<DeviceViewModel> deviceViewModels){
        this.deviceViewModels = deviceViewModels;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_item, parent, false);
        DeviceViewHolder deviceViewHolder = new DeviceViewHolder(view);
        return deviceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        holder.bindDevice(deviceViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return deviceViewModels.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder{

        View deviceView;
        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            this.deviceView = itemView;
        }

        public void bindDevice(DeviceViewModel deviceViewModel){
            TextView deviceName = deviceView.findViewById(R.id.deviceName);
            deviceName.setText(deviceViewModel.deviceName);
            if (deviceViewModel.connectionState == BluetoothAdapter.STATE_CONNECTED)
                deviceName.setTextColor(Color.GREEN);
            else
                deviceName.setTextColor(Color.RED);
        }
    }

}
