package com.example.mappe3kart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class EditMarker extends Activity {
    EditText innName, innAdresse;
    Button savebtn, delbtn;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmarker);

        innName = findViewById(R.id.name);
        innAdresse =findViewById(R.id.adresse);

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

    // funskjon som endrer marker, nå er det kun en tilbake knapp
    public void editIMarker (View v) {


        finish();
    }


    // funksjon som skal slette marker, nå sender den deg kun tilbake
    public void deleteMarker (View v) {


        finish();
    }
}
