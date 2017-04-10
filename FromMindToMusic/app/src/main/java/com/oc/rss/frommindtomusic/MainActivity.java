package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    File f,f1,f2,f3;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
         f = new File(Environment.getExternalStorageDirectory(), "FMTM");
        if (!f.exists()) {
            f.mkdirs();
        }
         f1 = new File(Environment.getExternalStorageDirectory()+"/"+"FMTM", "temp");
        if (!f1.exists()) {
            f1.mkdirs();
        }
         f2 = new File(Environment.getExternalStorageDirectory()+"/"+"FMTM", "perm");
        if (!f2.exists()) {
            f2.mkdirs();
        }
        f3 = new File(Environment.getExternalStorageDirectory()+"/"+"FMTM"+"/"+"perm","harmonized");
        if (!f3.exists()) {
            f3.mkdirs();
        }
        Button b= (Button) findViewById(R.id.Start);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent I = new Intent(MainActivity.this,RecordActivity.class);
                startActivity(I);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (f1.isDirectory())
        {
            File[] tempfiles = f1.listFiles();
            for (int i = 0; i < tempfiles.length; i++)
            {
                tempfiles[i].delete();

            }
        }
    }
}
