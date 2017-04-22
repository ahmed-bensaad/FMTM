package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by Raphael on 09/01/2017.
 */
public class HarmonizeActivity extends AppCompatActivity {
    String path=Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FMTM" + "/" + "perm" + "/"+"harmonized/" +
            "template" + RecordActivity.i + "AudioRecording.wav";
    String path1=Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FMTM" + "/" + "perm" + "/"+"harmonized/" +
            "template2" + RecordActivity.i + "AudioRecording.wav";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmonize);
        Button playh = (Button) findViewById(R.id.playh);
        playh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    playAudio(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Button stoph = (Button) findViewById(R.id.stoph);
        stoph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();

                }
            }
        });
        Button playh2 = (Button) findViewById(R.id.button8);
        playh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediaPlayer.setDataSource(path1);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                }

        });
        Button stoph2 = (Button) findViewById(R.id.button9);
        stoph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.add_menu,m);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        onClick();
        return super.onOptionsItemSelected(item);
    }
    public void onClick () {
        Intent m = new Intent(HarmonizeActivity.this,ChoiceActivity.class);
        startActivity(m);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }
    public void playAudio (View view) throws IOException
    {
       mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
