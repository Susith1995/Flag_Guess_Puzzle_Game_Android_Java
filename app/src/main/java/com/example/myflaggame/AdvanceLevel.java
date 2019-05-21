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

public class AdvanceLevel extends AppCompatActivity {



    private ImageView MyView2;
    private ImageView MyView;
    private ImageView MyView3;
    private ArrayList<String> cNameList;
    private String current_country_name;
    private ArrayList<String> countryCodeList;
    private int i=0;
    private ArrayList<String> loadedCList = new ArrayList<>();
    private Button butsubmit;
    private int count=0;
    private int wrongAttempts;
    private int attempts;
    private EditText textans,textans2,textans3;
    private TextView textA1,textA2,textA3;
    private String getans1,getans2,getans3;
    private int score;
    private TextView txtScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);


        countryCodeList = new ArrayList<>();
        countryCodeList = getCountryId("flags.json");

        cNameList = getCountyName("flags.json");

        MyView =  findViewById(R.id.imageView51);
        MyView2 =  findViewById(R.id.imageView52);
        MyView3 =  findViewById(R.id.imageView53);

        butsubmit = findViewById(R.id.button55);
        butsubmit.setText("submit");

         MyView.setImageResource(getResources().getIdentifier(countryCodeList.get(i).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        current_country_name = getCorrectName(countryCodeList.get(i));
        System.out.println(current_country_name);
        loadedCList.add(current_country_name);

        i++;


        MyView2.setImageResource(getResources().getIdentifier(countryCodeList.get(i).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        current_country_name = getCorrectName(countryCodeList.get(i));
        System.out.println(current_country_name);
        loadedCList.add(current_country_name);
        i++;


        MyView3.setImageResource(getResources().getIdentifier(countryCodeList.get(i).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        current_country_name = getCorrectName(countryCodeList.get(i));
        System.out.println(current_country_name);
        loadedCList.add(current_country_name);
        Log.d("loadlist", String.valueOf(loadedCList));
        i=0;

    }

     public void  checkAnswer(){

        attempts++;
         System.out.println(attempts);

          textans = (EditText) findViewById(R.id.editText51);
          getans1 = textans.getText().toString().toUpperCase();
         System.out.println("getans1:"+getans1);
         String correctans1 = loadedCList.get(0).toUpperCase();
         System.out.println("listname"+correctans1);
         textans2 = (EditText) findViewById(R.id.editText2);
         getans2 = textans2.getText().toString().toUpperCase();
         String correctans2 =  loadedCList.get(1).toUpperCase();

         textans3 = (EditText) findViewById(R.id.editText3);
         getans3 = textans3.getText().toString().toUpperCase();

         String correctans3 = loadedCList.get(2).toUpperCase();



         txtScore = (TextView) findViewById(R.id.score);
         if((getans1.equals(correctans1)) && (getans2.equals(correctans2)) && (getans3.equals(correctans3)) ) {
             System.out.println("score is 3");
            score=3;
             System.out.println(score);

             txtScore = (TextView) findViewById(R.id.score);
             txtScore.setText("score:3");
             txtScore.setTextColor(Color.parseColor("#f7080a"));



         }else if(getans1.equals(correctans1) && getans2.equals(correctans2)|| getans2.equals(correctans2) && getans3.equals(correctans3) ){
             System.out.println("score is 2");
             score=2;

             txtScore.setText("Score:2");
             txtScore.setTextColor(Color.parseColor("#f7080a"));

         }else if((!getans1.equals(correctans1)) && (!getans2.equals(correctans2)) && (!getans3.equals(correctans3))){
             score=0;
             txtScore.setText("Score:0");
             txtScore.setTextColor(Color.parseColor("#f7080a"));
         }

         else{
             System.out.println("score is 1");
             score=1;

             txtScore.setText("score:1");
             txtScore.setTextColor(Color.parseColor("#f7080a"));
         }


         if (getans1.equals(correctans1)) {

             /*Toast.makeText(AdvanceLevel.this, "text "+correctans1+" Correct!:"+getans1+"", Toast.LENGTH_LONG).show();*/
             textans.setTextColor(Color.parseColor("#5ec639"));
             textans.setEnabled(false);
             textans.setClickable(false);
             count++;
         }
         else {

             wrongAttempts++;
             textans.setTextColor(Color.parseColor("#f7080a"));
         }



         if (getans2.equals(correctans2)) {

             textans2.setTextColor(Color.parseColor("#5ec639"));
             textans2.setEnabled(false);
             textans2.setClickable(false);
             count++;

         }

         else {
             //make wrong answer red colour
             textans2.setTextColor(Color.parseColor("#f7080a"));

         }



         if (getans3.equals(correctans3)) {

             textans3.setTextColor(Color.parseColor("#5ec639"));
             textans3.setEnabled(false);
             textans3.setClickable(false);
             count++;
         }

         else {
             //make wrong answer red colour
             textans3.setTextColor(Color.parseColor("#f7080a"));
             wrongAttempts++;
         }

         System.out.println("total Score"+score);
         if(attempts>2 || score==3){
             System.out.println("next");
             butsubmit.setText("next");
             attempts=0;
         }


         System.out.println(count);


     }





    public void submitClick(View view) {

        System.out.println("is score passing"+score);
        textA1= findViewById(R.id.textViewA1);
        textA2= findViewById(R.id.textViewA2);
        textA3= findViewById(R.id.textViewA3);

        if(attempts==2){
            System.out.println("display correct answer");

            String correctans1 = loadedCList.get(0).toUpperCase();
            System.out.println("listname"+correctans1);

            System.out.println("same answer? "+getans1);

            if (getans1.equals(correctans1)){
                textA1.setText("");
            }else{
                textA1.setText(correctans1);
                textA1.setTextColor(Color.parseColor("#f7080a"));
            }

            textA2= findViewById(R.id.textViewA2);
            String correctans2 = loadedCList.get(1).toUpperCase();
            if (getans2.equals(correctans2)){
                textA2.setText("");
            }else{
                textA2.setText(correctans2);
                textA2.setTextColor(Color.parseColor("#f7080a"));
            }

            textA3= findViewById(R.id.textViewA3);
            String correctans3 = loadedCList.get(2).toUpperCase();
            if (getans3.equals(correctans3)){
                textA3.setText("");
            }else{
                textA3.setText(correctans3);
                textA3.setTextColor(Color.parseColor("#f7080a"));
            }

        }

        if(butsubmit.getText().toString().equals("submit")){
          checkAnswer();
          System.out.println("im in submit");



      }else{
          System.out.println("im in next");
          countryCodeList = new ArrayList<>();
          countryCodeList = getCountryId("flags.json");

          cNameList = getCountyName("flags.json");

          MyView =  findViewById(R.id.imageView51);
          MyView2 =  findViewById(R.id.imageView52);
          MyView3 =  findViewById(R.id.imageView53);
          butsubmit = findViewById(R.id.button55);

          butsubmit.setText("submit");
          loadedCList.clear();
          attempts=0;
          textans.setText("");
          textans.setEnabled(true);
          textans.setClickable(true);

          textans2.setText("");
          textans2.setEnabled(true);
          textans2.setClickable(true);

          textans3.setText("");
          textans3.setEnabled(true);
          textans3.setClickable(true);

            textA1.setText("");
            textA2.setText("");
            textA3.setText("");

            txtScore = (TextView) findViewById(R.id.score);
            txtScore.setText("");

          MyView.setImageResource(getResources().getIdentifier(countryCodeList.get(i).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
          current_country_name = getCorrectName(countryCodeList.get(i));
          System.out.println(current_country_name);
          loadedCList.add(current_country_name);

          i++;

          MyView2.setImageResource(getResources().getIdentifier(countryCodeList.get(i).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
          current_country_name = getCorrectName(countryCodeList.get(i));
          System.out.println(current_country_name);
          loadedCList.add(current_country_name);
          i++;


          MyView3.setImageResource(getResources().getIdentifier(countryCodeList.get(i).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
          current_country_name = getCorrectName(countryCodeList.get(i));
          System.out.println(current_country_name);
          loadedCList.add(current_country_name);
          Log.d("loadlist", String.valueOf(loadedCList));
          i=0;

          checkAnswer();
      }

    }






   //json operations

    public ArrayList<String> getCountyName(String filename) {

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
        Collections.shuffle(cNameList);

        return cNameList;


    }

    public String getCorrectName(String cId){
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
