package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.MainThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class RecordActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer ;

    public static final int RequestPermissionCode = 1;
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio,buttonValidate, buttonStopPlayingRecording;
    public static int i=1;


    private WavAudioRecorder mRecorder;
    private static final String mRcordFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FMTM/temp/testwave"+i+".wav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        buttonStart = (Button) findViewById(R.id.button);
        buttonStop = (Button) findViewById(R.id.button2);
        buttonPlayLastRecordAudio = (Button) findViewById(R.id.button3);
        buttonStopPlayingRecording = (Button) findViewById(R.id.button4);
        buttonValidate=(Button) findViewById(R.id.button5);
        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);
        buttonValidate.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               if (checkPermission()) {
                                                   mRecorder = WavAudioRecorder.getInstanse();
                                                   mRecorder.setOutputFile(mRcordFilePath);
                                                   if (WavAudioRecorder.State.INITIALIZING == mRecorder.getState()) {
                                                       mRecorder.prepare();
                                                       mRecorder.start();
                                                   } else if (WavAudioRecorder.State.ERROR == mRecorder.getState()) {
                                                       mRecorder.release();
                                                       mRecorder = WavAudioRecorder.getInstanse();
                                                       mRecorder.setOutputFile(mRcordFilePath);

                                                   } else {
                                                       mRecorder.stop();
                                                       mRecorder.reset();
                                                   }
                                                   i++;


                                               final Toast a=Toast.makeText(RecordActivity.this, "Recording starting",
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
                                               else {
                                                requestPermission();
                                                     }
                                                   buttonStart.setEnabled(false);
                                                   buttonStop.setEnabled(true);
                                               }
                                            });




       buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecorder.stop();
                mRecorder.reset();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonValidate.setEnabled(true);

                final Toast a=Toast.makeText(RecordActivity.this, "Recording complete",
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


        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {


                try {
                    playAudio(view);

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                final Toast a=Toast.makeText(RecordActivity.this, "Recording playing",
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

       buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                mediaPlayer.stop();
                mediaPlayer.release();

            }
        });

        buttonValidate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                Intent I = new Intent(RecordActivity.this,FirstInstrumentActivity.class);
                Thread trt = new TRThread(mRcordFilePath);
                trt.run();
                startActivity(I);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }
    public void playAudio (View view) throws IOException
    {
        buttonStop.setEnabled(false);
        buttonStart.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(true);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mRcordFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



            private void requestPermission() {
                ActivityCompat.requestPermissions(RecordActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
            }

            @Override
            public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
                switch (requestCode) {
                    case RequestPermissionCode:
                        if (grantResults.length > 0) {
                            boolean StoragePermission = grantResults[0] ==
                                    PackageManager.PERMISSION_GRANTED;
                            boolean RecordPermission = grantResults[1] ==
                                    PackageManager.PERMISSION_GRANTED;

                            if (StoragePermission && RecordPermission) {
                                final Toast a = Toast.makeText(RecordActivity.this, "Permission Granted",
                                        Toast.LENGTH_SHORT);
                                a.show();
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        a.cancel();
                                    }
                                }, 1000);
                            } else {
                                final Toast a = Toast.makeText(RecordActivity.this, "Permission Denied",
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
                        break;
                }
            }

            public boolean checkPermission() {
                int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                        WRITE_EXTERNAL_STORAGE);
                int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                        RECORD_AUDIO);
                return result == PackageManager.PERMISSION_GRANTED &&
                        result1 == PackageManager.PERMISSION_GRANTED;
            }
        }


