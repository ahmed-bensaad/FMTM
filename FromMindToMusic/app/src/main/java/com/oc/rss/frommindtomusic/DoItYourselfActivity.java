package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DoItYourselfActivity extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_it_yourself);
         spinner = (Spinner) findViewById(R.id.spinner);
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> instruments = new ArrayList<String>();
        instruments.add("Bass");
        instruments.add("Trumpet");
        instruments.add("Piano");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.spinner_item, instruments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

/*// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.instruments_array, R.layout.spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);*/
    }
    public void onClick (View v){

                Intent i = new Intent(DoItYourselfActivity.this, SingInstrumentActivity.class);
        String ch= spinner.getSelectedItem().toString();
        i.putExtra("instrum",ch);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


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
        Intent m = new Intent(DoItYourselfActivity.this, OrchestrateActivity.class);
        startActivity(m);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }
}
