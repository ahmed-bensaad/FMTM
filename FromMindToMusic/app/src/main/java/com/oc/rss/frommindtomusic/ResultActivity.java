package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.oc.rss.frommindtomusic.RecordActivity.i;

public class ResultActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button play,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

         play = (Button) findViewById(R.id.button10);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(true);

               String AudioSavePathInDevice =
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +"FMTM"+"/" +"temp"+"/"+
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

        stop = (Button) findViewById(R.id.button11);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(false);
                play.setEnabled(true);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    Toast.makeText(ResultActivity.this, "So,are you impressed?",
                            Toast.LENGTH_LONG).show();
                }
            }
            });
        Button save = (Button)findViewById(R.id.Save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+"FMTM"+"/" +"temp",
                        "template" +RecordActivity.i+ "AudioRecording.3gp",
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+"FMTM"+"/" +"perm");
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
        Intent m = new Intent(ResultActivity.this,ChoiceActivity.class);
        startActivity(m);

    }
    private void moveFile(String inputPath, String inputFile, String outputPath) {

        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File (outputPath);
            if (!dir.exists())
            {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath +"/"+ inputFile);
            out = new FileOutputStream(outputPath +"/"+ inputFile);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file
            out.flush();
            out.close();
            out = null;

            // delete the original file
            new File(inputPath +"/"+ inputFile).delete();


        }

        catch (FileNotFoundException fnfe1) {
            Log.e("tag", fnfe1.getMessage());
        }
        catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }




}
