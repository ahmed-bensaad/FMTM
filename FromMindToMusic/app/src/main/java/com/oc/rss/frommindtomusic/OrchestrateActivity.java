package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by Mathilde on 09/01/2017.
 */

public class OrchestrateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orchestrate);

    }
public void onClick (View v){
            Intent j = new Intent(OrchestrateActivity.this, DoItYourselfActivity.class);
            startActivity(j);
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
        Intent m = new Intent(OrchestrateActivity.this, ChoiceActivity.class);
        startActivity(m);
        overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);

    }
}
