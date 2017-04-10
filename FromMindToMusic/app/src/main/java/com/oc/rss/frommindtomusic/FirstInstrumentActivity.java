package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import Body.tonalité.*;

public class FirstInstrumentActivity extends AppCompatActivity {
    String s;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_instrument);

        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> instruments = new ArrayList<String>();
        instruments.add("Bass");
        instruments.add("Trumpet");
        instruments.add("Piano");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.spinner_item, instruments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*Toast a= Toast.makeText(FirstInstrumentActivity.this,s,
                Toast.LENGTH_SHORT);
        a.show();*/

    }


    public void onClick (View v) {
        s=spinner.getSelectedItem().toString();
        Play.jouer(TRThread.notes,s);
        Intent k = new Intent(FirstInstrumentActivity.this, ChoiceActivity.class);
        startActivity(k);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

    }
}
