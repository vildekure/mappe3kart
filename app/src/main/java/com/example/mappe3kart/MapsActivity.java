package com.example.mappe3kart;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mappe3kart.databinding.ActivityMapsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        getJSON task = new getJSON();
        task.execute(new
                String[]{"http://data1500.cs.oslomet.no/~s354592/jsonout.php"});

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(this);

        // Add a marker in Oslo and move the camera
        LatLng oslo = new LatLng(59.91, 10.75);
        mMap.addMarker(new MarkerOptions()
                .position(oslo)
                .title("Oslo")
                .snippet("Dette er Oslo"));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oslo));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Intent intent = new Intent(MapsActivity.this, EditMarker.class);
                startActivity(intent);
            }
        });
    }

    // skal ha en funksjon som lar en lage markers på kartet
    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        double latVar = latLng.latitude;
        double longVar = latLng.longitude;
        System.out.println(latVar + " " + longVar);
        MarkerOptions marker = new MarkerOptions()
                .position(latLng)
                .title("Test Marker");
        mMap.addMarker(marker);

    }

    private class getJSON extends AsyncTask<String, Void, String> {
        JSONObject jsonObject;

        @Override
        protected String doInBackground(String... urls) {
            String s = "";
            String output = "";

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
                    try {
                        JSONArray mat = new JSONArray(output);
                        for (int i = 0; i < mat.length(); i++) {
                            JSONObject jsonobject = mat.getJSONObject(i);

                            String latitude = jsonobject.getString("latitude");
                            String longitude = jsonobject.getString("longitude");
                            String name = jsonobject.getString("name");
                            String info = jsonobject.getString("info");
                            String adresse = jsonobject.getString("adresse");

                        }
                        return null;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                } catch (Exception e) {
                    return "Noe gikk galt";
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String ss) {

        }
    }
}