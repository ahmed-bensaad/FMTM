package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    switch (v.getId()){
        case R.id.autoButton:
            Intent i = new Intent(OrchestrateActivity.this, InstrumentsChoiceActivity.class);
            startActivity(i);
            break;
        case R.id.diyButton:
            //a completer
            break;
    }
}
}
