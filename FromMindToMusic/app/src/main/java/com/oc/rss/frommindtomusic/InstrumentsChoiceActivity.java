package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class InstrumentsChoiceActivity extends AppCompatActivity {
    private  ArrayList<Spinner> spinnersList=new ArrayList<Spinner>();

    private Spinner spinner1=null;
    private Spinner spinner2=null;
    int i=2;

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
        spinnersList.add((Spinner) findViewById(R.id.spinner11));
        spinnersList.add((Spinner) findViewById(R.id.spinner12));

       spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> instruments = new ArrayList<String>();
        instruments.add("Piano");
        instruments.add("Violon");
        instruments.add("Trompette");
        instruments.add("Sabre laser");

        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, instruments);
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
            }

        });

        Button add= (Button) findViewById(R.id.add);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner=spinnersList.get(i);
                spinner.setVisibility(View.VISIBLE);
                spinner.setAdapter(adapter);
                i++;
            }

        });
    }
}
