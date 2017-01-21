package com.oc.rss.frommindtomusic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Button result= (Button) findViewById(R.id.button6);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(ChoiceActivity.this,ResultActivity.class);
                startActivity(I);
            }

        });
        Button harmonize= (Button) findViewById(R.id.button7);
        harmonize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent In = new Intent(ChoiceActivity.this,HarmonizeActivity.class);
                startActivity(In);
            }

        });
       Button orchestrate= (Button) findViewById(R.id.orchestrateButton);
        orchestrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoiceActivity.this,OrchestrateActivity.class);
                startActivity(intent);
            }

        });

        Button style= (Button) findViewById(R.id.style);
        style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentS = new Intent(ChoiceActivity.this,StyleActivity.class);
                startActivity(intentS);
            }

        });
    }

}
