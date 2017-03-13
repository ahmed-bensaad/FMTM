package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.io.IOException;

public class SingInstrumentActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;
    String AudioSavePathInDevice;
    static boolean clicked = false;
    Chronometer recordchrono1;

    static int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_instrument);

        final Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked == false) {
                    clicked = true;
                    j++;
                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FMTM" + "/" + "temp" + "/" +
                                    "Demo_ins" + j + ".3gp";
                    MediaRecorderReady();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                        final Toast b = Toast.makeText(SingInstrumentActivity.this, "Record started",
                                Toast.LENGTH_SHORT);
                        b.show();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (clicked == true) {
                    clicked = false;
                    record.setBackgroundResource(R.drawable.image3398);
                    mediaRecorder.stop();
                    final Button Done = (Button) findViewById(R.id.ValidateIns);
                    Done.setEnabled(true);
                    final Toast a = Toast.makeText(SingInstrumentActivity.this, "Record completed",
                            Toast.LENGTH_SHORT);
                    a.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            a.cancel();
                        }
                    }, 1000);


                }
            }
        });


        Button playIns = (Button) findViewById(R.id.playIn);
        playIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                final Toast a = Toast.makeText(SingInstrumentActivity.this, "Record playing",
                        Toast.LENGTH_SHORT);
                a.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a.cancel();
                    }
                }, 1000);
            }
        });
        Button valid = (Button) findViewById(R.id.ValidateIns);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SingInstrumentActivity.this, SavedActivity.class);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                final Toast a = Toast.makeText(SingInstrumentActivity.this, "Sound added",
                        Toast.LENGTH_SHORT);
                a.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a.cancel();
                    }
                }, 1000);
                startActivity(i);

            }
        });


    }

    public void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.add_menu, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        onClick();
        return super.onOptionsItemSelected(item);
    }

    public void onClick() {
        Intent m = new Intent(SingInstrumentActivity.this, DoItYourselfActivity.class);
        startActivity(m);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }
}
