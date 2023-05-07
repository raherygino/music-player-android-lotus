package com.gsoft.lotus.view.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gsoft.lotus.databinding.ActivityMainBinding;
import com.gsoft.lotus.viewmodel.MusicViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MusicViewModel musicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);

        binding.btnPlay.setOnClickListener(v -> {
            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio.mp3";
            Uri uri = Uri.parse(filePath);
            musicViewModel.playMusic(MainActivity.this, uri);
            Toast.makeText(this, filePath, Toast.LENGTH_SHORT).show();
        });

        binding.btnStop.setOnClickListener(v -> musicViewModel.stopMusic());

    }
}