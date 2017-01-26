package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StyleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
    }


    public void onClick (View v){
        switch (v.getId()) {
            case R.id.jazzy:
                Toast.makeText(StyleActivity.this, "Go Jazzy :)",
                Toast.LENGTH_LONG).show();
                Intent i = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(i);
                break;
            case R.id.funky:
                Toast.makeText(StyleActivity.this, "Go funky :)",
                Toast.LENGTH_LONG).show();
                Intent j = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(j);
                break;
            case R.id.groovy:
                Toast.makeText(StyleActivity.this, "Go groovy :)",
                Toast.LENGTH_LONG).show();
                Intent k = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(k);
                break;
            case R.id.backButtonStyle:
                Intent m = new Intent(StyleActivity.this, ChoiceActivity.class);
                startActivity(m);
                break;
        }
    }



}