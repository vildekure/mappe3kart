package com.example.mappe3kart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class EditMarker extends Activity {
    EditText innName, innInfo;
    Button savebtn, delbtn, backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmarker);

        innName = findViewById(R.id.name);
        innInfo = findViewById(R.id.info);

        savebtn = findViewById(R.id.savebtn);
        delbtn = findViewById(R.id.delbtn);

        // test fullfør activity knapp er delete knapp
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
