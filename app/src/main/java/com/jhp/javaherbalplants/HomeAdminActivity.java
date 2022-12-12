package com.jhp.javaherbalplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        Button ToTanaman=findViewById(R.id.btnTOTanamanAdmin);
        Button TOMinuman=findViewById(R.id.btnTOMinumanAdmin);

        ToTanaman.setOnClickListener((view) ->{
            Intent in= new Intent(HomeAdminActivity.this,AdminTanamanActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });

        TOMinuman.setOnClickListener((view) ->{
            Intent in= new Intent(HomeAdminActivity.this,AdminMinumanActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
    }
}