package com.oc.rss.frommindtomusic;

import android.content.Intent;
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
        spinnersList.add((Spinner) findViewById(R.id.spinner9));
        spinnersList.add((Spinner) findViewById(R.id.spinner10));

       spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> instruments = new ArrayList<String>();
        instruments.add("Piano");
        instruments.add("Violon");
        instruments.add("Trompette");
        instruments.add("Clarinette");
        instruments.add("Sabre laser");

        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, instruments);
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
        if (i<10){
            Spinner spinner=spinnersList.get(i);
            spinner.setVisibility(View.VISIBLE);
            spinner.setAdapter(adapter);
            i++;}
        else {
            Toast.makeText(InstrumentsChoiceActivity.this, "10 instruments max", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(InstrumentsChoiceActivity.this, "1 instrument min", Toast.LENGTH_SHORT).show();
        }

    }

    }
