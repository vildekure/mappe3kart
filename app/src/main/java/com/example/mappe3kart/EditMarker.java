package com.example.mappe3kart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class EditMarker extends Activity {
    TextInputEditText innName, innInfo, innAdress;
    Button delbtn, savebtn;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmarker);

        /*
        getJSON task = new getJSON();
        task.execute(new
                String[]{"http://data1500.cs.oslomet.no/~s354592/jsonin.php});
         */

        innName = findViewById(R.id.nameInput);
        innInfo = findViewById(R.id.infoInput);
        innAdress = findViewById(R.id.adrInput);

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
