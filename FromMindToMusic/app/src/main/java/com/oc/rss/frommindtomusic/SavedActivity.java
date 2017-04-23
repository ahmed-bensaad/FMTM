package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SavedActivity extends AppCompatActivity {
String path= Environment.getExternalStorageDirectory().getAbsolutePath() + "/FMTM/perm/orchestrated/template"+ SingInstrumentActivity.j+".wav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        Button play = (Button)findViewById(R.id.end);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playAudio(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button more = (Button)findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (SavedActivity.this,DoItYourselfActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);
            }
        });
    }
    public void playAudio (View view) throws IOException
    {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
