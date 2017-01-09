package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static com.oc.rss.frommindtomusic.RecordActivity.i;

public class ResultActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button play = (Button) findViewById(R.id.button10);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String AudioSavePathInDevice =
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                "template" +i+ "AudioRecording.3gp";

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(ResultActivity.this, "Result Playing",
                        Toast.LENGTH_LONG).show();
            }
        });

        Button stop = (Button) findViewById(R.id.button11);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    Toast.makeText(ResultActivity.this, "So,are you impressed?",
                            Toast.LENGTH_LONG).show();
                }
            }
            });
        Button back = (Button) findViewById(R.id.button13);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back= new Intent(ResultActivity.this,ChoiceActivity.class);
                startActivity(back);
            }
        });
    }

}
