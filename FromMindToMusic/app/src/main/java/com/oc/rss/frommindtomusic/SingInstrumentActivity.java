package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
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

import Body.accompagnement.Orchestrate;
import Body.accompagnement.PlayArray;
import Body.transcription.Transcribe;

public class SingInstrumentActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    String AudioSavePathInDevice;
    static boolean clicked = false;
    Chronometer recordchrono2;
    private WavAudioRecorder mRecorder;

    public static int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_instrument);
        recordchrono2=(Chronometer) findViewById(R.id.recordchrono2);
        AudioSavePathInDevice =
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "FMTM" + "/" + "temp" + "/" +
                        "Demo_ins" + j + ".wav";

        final Button record = (Button) findViewById(R.id.record);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked == false) {
                    clicked = true;
                    recordchrono2.start();

                        mRecorder = WavAudioRecorder.getInstanse();
                        mRecorder.setOutputFile(AudioSavePathInDevice);
                        if (WavAudioRecorder.State.INITIALIZING == mRecorder.getState()) {
                            mRecorder.prepare();
                            mRecorder.start();
                        } else if (WavAudioRecorder.State.ERROR == mRecorder.getState()) {
                            mRecorder.release();
                            mRecorder = WavAudioRecorder.getInstanse();
                            mRecorder.setOutputFile(AudioSavePathInDevice);

                        } else {
                            mRecorder.stop();
                            mRecorder.reset();
                        }



                        final Toast a = Toast.makeText(SingInstrumentActivity.this, "Recording", Toast.LENGTH_SHORT);
                        a.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                a.cancel();
                            }
                        }, 1000);

                } else if (clicked = true) {
                    clicked = false;
                    record.setBackgroundResource(R.drawable.image3398);
                    mRecorder.stop();
                    recordchrono2.stop();
                    mRecorder.reset();




                } else if (clicked == true) {
                    clicked = false;
                    record.setBackgroundResource(R.drawable.image3398);

                    recordchrono2.stop();
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
                try {
                    playAudio(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
                double[][] newNotes = Transcribe.transcribe(AudioSavePathInDevice).getNotes() ;
                String ch=getIntent().getStringExtra("instrum");
                TRThread.setArray(Orchestrate.orchestrate(TRThread.array,newNotes,ch));
                PlayArray.Play(TRThread.getArray());
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


    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.add_menu, m);
        return true;
    }

        Intent m = new Intent(SingInstrumentActivity.this, DoItYourselfActivity.class);
        public boolean onOptionsItemSelected(MenuItem item) {
            onClick();
            return super.onOptionsItemSelected(item);
        }

    public void onClick() {
        startActivity(m);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }
    public void playAudio (View view) throws IOException
    {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(AudioSavePathInDevice);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
