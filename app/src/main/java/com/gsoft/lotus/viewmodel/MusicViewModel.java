package com.gsoft.lotus.viewmodel;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;

public class MusicViewModel extends ViewModel {
    private MediaPlayer mediaPlayer;
    private MutableLiveData<Boolean> isPlaying = new MutableLiveData<>();

    public MusicViewModel() {
        mediaPlayer = new MediaPlayer();
        isPlaying.setValue(false);
    }

    public MutableLiveData<Boolean> getIsPlaying() {
        return isPlaying;
    }

    public void playMusic(Context context, Uri uri) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }

            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();

            isPlaying.setValue(true);

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        isPlaying.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mediaPlayer.release();
    }
}

