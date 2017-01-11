package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DoItYourselfActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_it_yourself);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.instruments_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }
    public void onClick (View v){
        switch (v.getId()){
            case R.id.singNewButton:
                Intent i = new Intent(DoItYourselfActivity.this, SingInstrumentActivity.class);
                startActivity(i);
                break;
            case R.id.backButtonDIY:
                Intent k = new Intent(DoItYourselfActivity.this, OrchestrateActivity.class);
                startActivity(k);
                break;}
}
}
