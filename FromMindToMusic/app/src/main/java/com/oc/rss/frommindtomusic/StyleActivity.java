package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
                final Toast a=Toast.makeText(StyleActivity.this, "Go Jazzy",
                        Toast.LENGTH_SHORT);
                a.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        a.cancel();
                    }
                }, 1000);
                Intent i = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(i);
                break;
            case R.id.funky:
                final Toast b=Toast.makeText(StyleActivity.this, "Go Funky",
                        Toast.LENGTH_SHORT);
                b.show();
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.cancel();
                    }
                }, 1000);
                Intent j = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(j);
                break;
            case R.id.groovy:
                final Toast c=Toast.makeText(StyleActivity.this, "Go Groovy",
                        Toast.LENGTH_SHORT);
                c.show();
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        c.cancel();
                    }
                }, 1000);
                Intent k = new Intent(StyleActivity.this, ResultActivity.class);
                startActivity(k);
                break;

        }
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
        Intent m = new Intent(StyleActivity.this, ChoiceActivity.class);
        startActivity(m);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }



}