package com.gsoft.lotus.services;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.gsoft.lotus.viewmodel.MusicViewModel;

public class MusicService extends Service {
    private MusicViewModel musicViewModel;
    private Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        musicViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MusicViewModel.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicViewModel.stopMusic();
    }

    public static boolean isServiceRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (MusicService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}

