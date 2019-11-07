package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    SeekBar volumeRocker, timeline;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.testaudio);

        //get the context of audio service

        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int myMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int myCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        //set context to seekbar - volumeRocker


        volumeRocker = findViewById(R.id.seekBar);
        volumeRocker.setMax(myMaxVolume);
        volumeRocker.setProgress(myCurrentVolume);

        //Set a listener on volumeRocker

        volumeRocker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Timeline part of the music app

        timeline = findViewById(R.id.timeline);
        timeline.setMax(mediaPlayer.getDuration());// set the max of the seek bar as the
                                                   // duration of the auido

        //Set onchange listener on Timeline


        timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Coustomize timeline seekbar


        //below class is to make sure that seekbar is moving along with the time of audio

        //new Timer().scheduleAtFixedRate(----,0,1000); At the end type new TimerTask(its tricky so)
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                timeline.setProgress(mediaPlayer.getCurrentPosition());

            }
        }, 0, 1000);


    }
    public void playMe(View view){
        mediaPlayer.start();

    }
    public void pauseMe(View view){
        mediaPlayer.pause();
    }
}
