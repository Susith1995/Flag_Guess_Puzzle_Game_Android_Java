package com.example.myflaggame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;

public class GuessHints extends AppCompatActivity {

    private ArrayList<String> countryCodeList;
    private String hidden_country_name;
    private String current_country_name;
    private EditText user_guess;
  //  int i = 0;
    private ImageView myImageView;
    TextView myTextView;
    Button submit;
    private int k = 0;
    private int j;
    private int wrongAttempts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        myTextView = findViewById(R.id.textView5);
        user_guess = findViewById(R.id.editText13);
        myImageView = findViewById(R.id.imageView11);

        submit = findViewById(R.id.button13);
        submit.setText("add");

        countryCodeList = new ArrayList<>();
        countryCodeList = getCountryId("flags.json");

        System.out.println(countryCodeList.toString());
        setFlag();
        hideCountryName();


    }

    public void addClick(View view) {        //until click button label as add,below functions execute

        if (submit.getText().toString().equals("add")) {
            checkAnswers();
        }
        else {
            submit.setText("add");
            System.out.println(submit.getText().toString());
            setFlag();
            user_guess.setText("");
            j++;
            hideCountryName();
            checkAnswers();
        }
    }

    public void setFlag() {    //set image of flag to the imageview


        myImageView.setImageResource(getResources().getIdentifier(countryCodeList.get(k).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        current_country_name = getCorrectName(countryCodeList.get(k)); // country name of the related image
        k++;


    }

    private void hideCountryName() {     //hide the letters and show length of guessing word

        current_country_name = getCorrectName(countryCodeList.get(j));
        hidden_country_name = current_country_name.replaceAll("[A-Z]", "-");
        myTextView.setText(hidden_country_name);

    }


    //json operations
    private void checkAnswers() {


        String guessed_char = user_guess.getText().toString().toUpperCase(); //user input char

        Log.d("current_country_name", current_country_name);

        if (current_country_name.toUpperCase().contains(guessed_char)) {
            Log.d("msg", "part1");
            int index = current_country_name.indexOf(guessed_char);
            for (int i = 0; i < current_country_name.length(); i++) {
                if (("" + current_country_name.charAt(i)).equals(guessed_char)) {
                    hidden_country_name = hidden_country_name.substring(0, i) + current_country_name.charAt(i) + hidden_country_name.substring(i + 1);
                }
            }

            myTextView.setText(hidden_country_name);
        } else {
            Log.d("msg", " im in part2");

            Toast.makeText(GuessHints.this, "Wrong Guess!", Toast.LENGTH_SHORT).show();
            wrongAttempts++;
            System.out.println(wrongAttempts);


        }
        System.out.println(wrongAttempts);


        if (hidden_country_name.equals(current_country_name)) {
            myTextView.setText("CORRECT!");
            myTextView.setTextColor(Color.parseColor("#5ec639"));
            submit = findViewById(R.id.button13);
            submit.setText("next");
        } else if (wrongAttempts > 3) {
            submit.setText("next");
            //display incorrect
            System.out.println("wrong\n" + current_country_name + "");
            myTextView.setText("WRONG!\n" + current_country_name + "");
            wrongAttempts = 0;

        }

        user_guess.setText("");

    }


    public ArrayList<String> getCountryId(String filename) {   // load the PNG image using jsonfile's cid

        JSONArray jsonArrayid = null;
        ArrayList<String> cidList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(filename);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArrayid = new JSONArray(json);
            if (jsonArrayid != null) {
                for (int i = 0; i < jsonArrayid.length(); i++) {
                    cidList.add(jsonArrayid.getJSONObject(i).getString("cid"));

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }
        Collections.shuffle(cidList);  //shuffle the countryid to get random sequence

        return cidList;

    }

    public String getCorrectName(String cId) {  //json operation to get the name of the lodaed flag
        JSONArray jsonArray = null;
        //ArrayList cNameList = new ArrayList<String>();
        boolean found = false;
        String correctFlagName = "";
        int i = 0;

        try {
            InputStream is = getResources().getAssets().open("flags.json");
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);


            if (jsonArray != null) {

                while (!found) {

                    try {

                        String s1 = jsonArray.getJSONObject(i).getString("cid").toUpperCase();
                        String s2 = cId.toUpperCase();
                        if (s1.equals(s2)) {
                            found = true;
                            correctFlagName = jsonArray.getJSONObject(i).getString("cname").toUpperCase();
                        } else {
                            i++;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return correctFlagName;
    }


}
