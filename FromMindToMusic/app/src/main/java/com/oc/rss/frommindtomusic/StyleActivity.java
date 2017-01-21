package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StyleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
    }


    public void onClick (View v){
        switch (v.getId()) {
            case R.id.jazzy:
                Intent i = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(i);
                break;
            case R.id.funky:
                Intent j = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(j);
                break;
            case R.id.groovy:
                Intent k = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(k);
                break;
        }
    }
}