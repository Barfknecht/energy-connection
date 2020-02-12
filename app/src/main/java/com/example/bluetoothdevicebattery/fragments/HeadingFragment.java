package com.example.bluetoothdevicebattery.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bluetoothdevicebattery.managers.NotificationManager;
import com.example.bluetoothdevicebattery.models.NotificationModel;
import com.example.bluetoothdevicebattery.R;

public class HeadingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View headingFragment = inflater.inflate(R.layout.fragment_heading, container, false);
        final NotificationManager notificationManager = new NotificationManager(getActivity());
        final NotificationModel notificationModel = new NotificationModel("Title", "Content", 100);
        Switch enableNotification = headingFragment.findViewById(R.id.enable_notification);
        enableNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    notificationManager.showNotification(notificationModel);
                else
                    notificationManager.updateNotification();

            }
        });

        return headingFragment;
    }
}
