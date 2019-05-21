package com.example.myflaggame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;


public class GuessCountry extends AppCompatActivity {

    public Spinner spinner1;
    public ImageView myImageView;
    public ArrayList<String> cidList;
    private ArrayList<String> cNameList;
    String cid_of_current_flag;
    Button submit_btn;
    TextView text11;
    TextView text12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        submit_btn = findViewById(R.id.button1);  //Initialize buttons,text views,image view
        text11 = findViewById(R.id.textView4);
        text12 = findViewById(R.id.textView6);
        myImageView = findViewById(R.id.imageView4);


        setFlag();

        spinner1 = findViewById(R.id.spinner1);
        ArrayList<String> listitems = getCountryName("flags.json");   //arraylist used for store names of country from flags.json file
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout, R.id.txt, listitems);
        spinner1.setAdapter(adapter);


    }

    public void setFlag() {

        cidList = getCountryId("flags.json"); //intialize array cidlist

        //loading the relevant image to image view which is same id in cidlist first index after shuffling
        myImageView.setImageResource(getResources().getIdentifier(cidList.get(0).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        cid_of_current_flag = cidList.get(0);

    }

    public void submitFlag(View view) {

        if (submit_btn.getText().toString().equals("Submit")) {      //if button label is submit, it checks the answer correct or not
            String text = spinner1.getSelectedItem().toString().toUpperCase();

            String text2 = getCorrectName(cid_of_current_flag);

            Log.d("selected_text", text);
            Log.d("correct name", text2);

            if (text.equals(text2)) {

                TextView text11 = (TextView) findViewById(R.id.textView4);
                text11.setText("Answer Correct!");
                text11.setTextColor(Color.parseColor("#5ec639"));

            } else {


                text11.setText("Wrong!");
                text12.setText(text2);   //display correct answer when wrong
                text12.setTextColor(Color.parseColor("#0009ff"));
                text11.setTextColor(Color.parseColor("#f7080a"));

            }

            submit_btn.setText("Next"); //finaly set the button label as "NEXT"

        } else if (submit_btn.getText().toString().equals("Next")) {     //if button label is next,direct to new window
            setFlag();
            submit_btn.setText("Submit");
            text11.setText("");
            text12.setText("");
            text11.setTextColor(Color.BLACK);

        }

    }


    //json Oprations

    public String getCorrectName(String cId) {
        JSONArray jsonArray = null;
        cNameList = new ArrayList<String>();
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


    public ArrayList<String> getCountryName(String filename) {

        JSONArray jsonArray = null;
        cNameList = new ArrayList<String>();
        try {
            InputStream is = getResources().getAssets().open(filename);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            jsonArray = new JSONArray(json);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    cNameList.add(jsonArray.getJSONObject(i).getString("cname"));

                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
        }


        return cNameList;

    }

    public ArrayList<String> getCountryId(String filename) {

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
        Collections.shuffle(cidList);

        return cidList;

    }


}


