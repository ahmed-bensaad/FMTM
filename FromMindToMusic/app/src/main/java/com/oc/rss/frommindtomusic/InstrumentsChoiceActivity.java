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
    private Spinner listeInstruments=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruments_choice);

        listeInstruments = (Spinner) findViewById(R.id.instruments);
        List<String> instruments = new ArrayList<String>();
        instruments.add("Piano");
        instruments.add("Violon");
        instruments.add("Trompette");
        instruments.add("Sabre laser");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, instruments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listeInstruments.setAdapter(adapter);

        Button ok= (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstrumentsChoiceActivity.this,ResultActivity.class);
                startActivity(intent);
            }

        });
    }
}
