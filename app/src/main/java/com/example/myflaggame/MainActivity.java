package com.example.myflaggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void country(View viewcountry) {

        Intent country = new Intent(MainActivity.this, GuessCountry.class);  //direct to new intent

        startActivity(country);
    }

    public void guessHints(View v2) {

        Intent hints = new Intent(MainActivity.this, GuessHints.class);

        startActivity(hints);
    }


    public void guessFlag(View view) {
        Intent threeflags = new Intent(MainActivity.this, GuessFlag.class);

        startActivity(threeflags);
    }

    public void advancedLevel(View view) {
        Intent advanced = new Intent(MainActivity.this, AdvanceLevel.class);

        startActivity(advanced);
    }
}
