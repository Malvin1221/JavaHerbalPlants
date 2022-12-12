package com.jhp.javaherbalplants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivityMinuman extends AppCompatActivity {

    TextView detailDescMinuman, detailTitleMinuman, detailLangMinuman;
    ImageView detailImageMinuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_minuman);

        detailDescMinuman = findViewById(R.id.detailDescMinuman);
        detailImageMinuman = findViewById(R.id.detailImageMinuman);
        detailTitleMinuman = findViewById(R.id.detailTitleMinuman);
        detailLangMinuman = findViewById(R.id.detailLangMinuman);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailDescMinuman.setText(bundle.getString("Description"));
            detailTitleMinuman.setText(bundle.getString("Title"));
            detailLangMinuman.setText(bundle.getString("Lang"));
            Glide.with(this).load(bundle.getString("Image")).into(detailImageMinuman);
        }
    }
}