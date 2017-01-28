package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class SingInstrumentActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;
    String AudioSavePathInDevice;
    static int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_instrument);
        final Button recordIn= (Button)findViewById(R.id.recordIn);
        recordIn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    recordIn.setBackgroundResource(android.R.drawable.presence_audio_away);
                    j++;
                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    "Demo_ins" +j+ ".3gp";
                    MediaRecorderReady();
                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    recordIn.setBackgroundResource(android.R.drawable.presence_audio_online);
                    try {
                        mediaRecorder.stop();
                        final Button Done = (Button) findViewById(R.id.ValidateIns);
                        Done.setEnabled(true);
                        Toast.makeText(SingInstrumentActivity.this, "Recording Completed",
                         Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        Toast.makeText(SingInstrumentActivity.this, "You must hold this button to record",
                                Toast.LENGTH_LONG).show();

                    }
                }
                return true;

            }
        });
        Button playIns =(Button)findViewById(R.id.playIn);
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
                Toast.makeText(SingInstrumentActivity.this, "Recording Playing",
                        Toast.LENGTH_LONG).show();
            }
        });
        Button valid=(Button)findViewById(R.id.ValidateIns);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SingInstrumentActivity.this,ResultActivity.class);
                Toast.makeText(SingInstrumentActivity.this, "Sound added",
                        Toast.LENGTH_LONG).show();
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
}
