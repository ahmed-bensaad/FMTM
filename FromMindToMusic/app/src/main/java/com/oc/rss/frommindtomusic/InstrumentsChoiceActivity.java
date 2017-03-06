package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;




public class InstrumentsChoiceActivity extends AppCompatActivity {
    private  ArrayList<Spinner> spinnersList=new ArrayList<Spinner>();

    private Spinner spinner1=null;
    private Spinner spinner2=null;
    int i=2;
    private ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruments_choice);

        spinnersList.add((Spinner) findViewById(R.id.spinner1));
        spinnersList.add((Spinner) findViewById(R.id.spinner2));
        spinnersList.add((Spinner) findViewById(R.id.spinner3));
        spinnersList.add((Spinner) findViewById(R.id.spinner4));
        spinnersList.add((Spinner) findViewById(R.id.spinner5));
        spinnersList.add((Spinner) findViewById(R.id.spinner6));
        spinnersList.add((Spinner) findViewById(R.id.spinner7));
        spinnersList.add((Spinner) findViewById(R.id.spinner8));

       spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> instruments = new ArrayList<String>();
        instruments.add("Trompette");
        instruments.add("Basse");

        adapter= new ArrayAdapter<String>(this,R.layout.spinner_item, instruments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);




        Button ok= (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstrumentsChoiceActivity.this,ResultActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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
        Intent k = new Intent(InstrumentsChoiceActivity.this, OrchestrateActivity.class);
        startActivity(k);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }
    public void add(View v){
        if (i<8){
            Spinner spinner=spinnersList.get(i);
            spinner.setVisibility(View.VISIBLE);
            spinner.setAdapter(adapter);
            i++;}
        else {
            final Toast a=Toast.makeText(InstrumentsChoiceActivity.this, "8 instruments max",
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
    public void remove(View v){
        if (i>1){
            i--;
            Spinner spinner=spinnersList.get(i);
            spinner.setVisibility(View.INVISIBLE);
            spinner.setAdapter(adapter);
            }
        else {
            final Toast a=Toast.makeText(InstrumentsChoiceActivity.this, "1 instrument min",
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

    }
