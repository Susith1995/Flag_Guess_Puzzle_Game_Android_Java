package com.example.myflaggame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.util.List;

public class GuessFlag extends AppCompatActivity {


    private ArrayList<String> cNameList;
    private ImageView myView;
    private ImageView myView2;
    private ImageView myView3;
    private String current_cId;
    private int click;

    private ArrayList<String> countryCodeList;
    private int i=0;
    private TextView text12;
    private int k=0;
    private String shuffled_name;
    ArrayList<String> shuffleList = new ArrayList<String>();
    private String loadedcountryName;
    private TextView text13;

    private int v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
         TextView txtansstatus= findViewById(R.id.textView42);
        txtansstatus.setText("");


        countryCodeList = new ArrayList<>();
        countryCodeList = getCountryId("flags.json");

        cNameList = getCountyName("flags.json");

         text12 = (TextView) findViewById(R.id.textView41);


        myView = (ImageView) findViewById(R.id.imageView51);
        myView2 = (ImageView) findViewById(R.id.imageView52);
        myView3 = (ImageView) findViewById(R.id.imageView53);

         text13 = (TextView) findViewById(R.id.textView42);


        basics();

    }


    public ArrayList<String> basics(){

        shuffleList.clear();


        loadedcountryName = (getCorrectName(countryCodeList.get(i)));
        text12.setText(loadedcountryName);


        ArrayList<String> loadedimage = new ArrayList<String>();

        current_cId = countryCodeList.get(i);
        loadedimage.add(current_cId);
        System.out.println(loadedimage);
        i++;


        current_cId = countryCodeList.get(i);

        loadedimage.add(current_cId);
        i++;

        
        current_cId = countryCodeList.get(i);

        loadedimage.add(current_cId);

        System.out.println(loadedimage);

        Collections.shuffle(loadedimage);


        myView.setImageResource(getResources().getIdentifier(loadedimage.get(k).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        shuffled_name = getCorrectName(loadedimage.get(k));
        shuffleList.add(shuffled_name);
        System.out.println("aftershuffle1"+shuffleList);
        k++;

        myView2.setImageResource(getResources().getIdentifier(loadedimage.get(k).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        shuffled_name = getCorrectName(loadedimage.get(k));
        shuffleList.add(shuffled_name);
        k++;

        myView3.setImageResource(getResources().getIdentifier(loadedimage.get(k).toLowerCase(), "drawable", getApplicationContext().getPackageName()));
        shuffled_name = getCorrectName(loadedimage.get(k));

        shuffleList.add(shuffled_name);
        System.out.println("aftershuffle3"+shuffleList);

        k=0;



return shuffleList;
    }

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


    public void clickimage1(View view) {
       // Toast.makeText(GuessFlag.this, "clicked image 1!:", Toast.LENGTH_LONG).show();
        click = 1;

        if(shuffleList.get(0).equals(loadedcountryName)){
            System.out.println(shuffleList.get(v));
            text13.setText("correct!");
            text13.setTextColor(Color.parseColor("#32ad06"));
        }else{
            text13.setText("wrong!");
            text13.setTextColor(Color.parseColor("#f7080a"));
        }

    }

    public void clickimage2(View view) {

        click =2;
        if(shuffleList.get(1).equals(loadedcountryName)){
            text13.setText("correct!");
            text13.setTextColor(Color.parseColor("#32ad06"));
        }else{
            text13.setText("wrong!");
            text13.setTextColor(Color.parseColor("#f7080a"));
        }
    }

    public void clickimage3(View view) {

        click=3;
        if(shuffleList.get(2).equals(loadedcountryName)){
            text13.setText("correct!");
            text13.setTextColor(Color.parseColor("#32ad06"));
        }else{
            text13.setText("wrong!");
            text13.setTextColor(Color.parseColor("#f7080a"));
        }
    }

    public void submit_next(View view) {
        text13.setText("");
      basics();

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



}
