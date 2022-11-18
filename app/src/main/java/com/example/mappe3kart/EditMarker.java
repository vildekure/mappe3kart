package com.example.mappe3kart;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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
    ImageButton backbtn;
    Double latitude, longitude;
    String dbName, dbInfo, address;
    int dbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmarker);

        Intent hent = getIntent();

        // Fra klikk
        latitude = hent.getDoubleExtra("latitude", 0);
        longitude = hent.getDoubleExtra("longitude", 0);

        // Fra info Window
        dbId = hent.getIntExtra("dbId", -1);
        dbName = hent.getStringExtra("dbName");
        dbInfo = hent.getStringExtra("dbInfo");
        address = hent.getStringExtra("adresse");

        System.out.println(dbId);

        innName = findViewById(R.id.nameInput);
        innInfo = findViewById(R.id.infoInput);
        innAdress = findViewById(R.id.adrInput);

        //fyll inn info i feltene
        innAdress.setText(address);
        innName.setText(dbName);
        innInfo.setText(dbInfo);

        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // funskjon som endrer marker
    public void saveMarker(View v) {
        String name = innName.getText().toString();
        String info = innInfo.getText().toString();

        if (name.matches("")) {
            Toast.makeText(this, "Skriv inn navn på severdighet", Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("Severdighet med koordinater " + latitude + " " + longitude + " er laget");

        if (dbId == -1) {
            // Legg til
            getJSON jsonin = new getJSON();
            String newUrl = "http://data1500.cs.oslomet.no/~s354592/jsonin.php?Latitude=" + latitude +
                    "&Longitude=" + longitude + "&Name=" + name + "&Info=" + info +
                    "&Adresse=" + address;

            jsonin.execute(newUrl);
        } else {
            // Endre
            getJSON jsonendre = new getJSON();
            String changeUrl = "http://data1500.cs.oslomet.no/~s354592/jsonendre.php?Id="
                    + dbId + "&Name=" + name + "&Info=" + info;

            jsonendre.execute(changeUrl);
        }
    }


    // funksjon som skal slette marker
    public void deleteMarker(View v) {
        getJSON jsondelete = new getJSON();

        String url = "http://data1500.cs.oslomet.no/~s354592/jsondelete.php?id=" + dbId;
        jsondelete.execute(url);
    }

    private class getJSON extends AsyncTask<String, Void, Boolean> {
        JSONObject jsonObject;

        @Override
        protected Boolean doInBackground(String... urls) {
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
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                setResult(Activity.RESULT_CANCELED);
            } else {
                setResult(Activity.RESULT_OK);
            }
            finish();
        }
    }
}
