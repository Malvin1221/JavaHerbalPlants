package com.jhp.javaherbalplants;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ImageView imgTOTanaman=findViewById(R.id.imgTOTanaman);
        ImageView imgToMinuman=findViewById(R.id.imgTOMinuman);

        imgTOTanaman.setOnClickListener((view) ->{
            Intent in= new Intent(HomeActivity.this,userTanamanActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });

        imgToMinuman.setOnClickListener((view) ->{
            Intent in= new Intent(HomeActivity.this,userMinumanActivity.class);
            in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(in);
        });
    }
}