package com.example.mappe3kart;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.mappe3kart.Models.Severdighet;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EditMarker extends Activity {
    TextInputEditText innName, innInfo, innAdress;
    Button delbtn, savebtn;
    ImageButton backbtn;
    Double latitude, longitude;
    String klikkAdresse, dbLat, dbLong, dbName, dbInfo, dbAddress;
    int dbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmarker);

        Intent hent = getIntent();

        // Fra klikk
        latitude = hent.getDoubleExtra("latitude", 0);
        longitude = hent.getDoubleExtra("longitude", 0);
        klikkAdresse = hent.getStringExtra("klikkAdresse");

        // Fra info Window
        dbId = hent.getIntExtra("dbId", 0);
        dbLat = hent.getStringExtra("dbLat");
        dbLong = hent.getStringExtra("dbLong");
        dbName = hent.getStringExtra("dbName");
        dbInfo = hent.getStringExtra("dbInfo");
        dbAddress = hent.getStringExtra("dbAdresse");

        System.out.println(dbId);

        innName = findViewById(R.id.nameInput);
        innInfo = findViewById(R.id.infoInput);
        innAdress = findViewById(R.id.adrInput);

        //fyll inn info i feltene
        innAdress.setText(klikkAdresse);
        // denne overkjører den over, lage if setning???
        innAdress.setText(dbAddress);
        innName.setText(dbName);
        innInfo.setText(dbInfo);

        savebtn = findViewById(R.id.savebtn);
        delbtn = findViewById(R.id.delbtn);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // funskjon som endrer marker
    public void saveMarker (View v) {
        getJSON jsonin = new getJSON();

        String name = innName.getText().toString();
        String info = innInfo.getText().toString();
        String adresse = innAdress.getText().toString();

        System.out.println("Severdig het med koordinater " + latitude + " " + longitude + " er laget");

        String url = "http://data1500.cs.oslomet.no/~s354592/jsonin.php?Latitude=" + latitude +
                "&Longitude=" + longitude + "&Name=" + name + "&Info=" + info +
                "&Adresse=" + adresse;

        jsonin.execute(url);

        /* Må lage til endring og
        getJSON jsonendre = new getJSON();
        task.execute(new
                String[]{"http://data1500.cs.oslomet.no/~s354592/jsonendre.php"});
         */
    }


    // funksjon som skal slette marker
    public void deleteMarker (View v) {
        getJSON jsondelete = new getJSON();

        String url = "http://data1500.cs.oslomet.no/~s354592/jsondelete.php?id=" + dbId;
        jsondelete.execute(url);
    }

    private class getJSON extends AsyncTask<String, Void, List<Severdighet>> {
        JSONObject jsonObject;

        @Override
        protected List<Severdighet> doInBackground(String... urls) {
            String s = "";
            String output = "";
            System.out.println("Er i doInBackground");

            for (String url : urls) {
                try {
                    URL urlen = new URL(urls[0]);
                    HttpURLConnection conn = (HttpURLConnection)
                            urlen.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept",
                            "application/json");
                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + conn.getResponseCode());
                    }
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));
                    System.out.println("Output from Server .... \n");
                    while ((s = br.readLine()) != null) {
                        output = output + s;
                    }
                    conn.disconnect();
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Severdighet> severdigheter) {
            finish();
        }
    }

}
