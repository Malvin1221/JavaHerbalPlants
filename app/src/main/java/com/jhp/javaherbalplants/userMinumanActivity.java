package com.jhp.javaherbalplants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class userMinumanActivity extends AppCompatActivity {

    RecyclerView recyclerViewMinuman;
    List<DataClassMinuman> dataListMinuman;
    DatabaseReference databaseReferenceMinuman;
    ValueEventListener eventListener;
    SearchView searchView;
    MyAdapterMinuman adapterMinuman;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_minuman);

        recyclerViewMinuman = findViewById(R.id.recyclerViewMinuman);
        searchView = findViewById(R.id.searchMinuman);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(userMinumanActivity.this,2);
        recyclerViewMinuman.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(userMinumanActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layoutminuman);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataListMinuman = new ArrayList<>();

        adapterMinuman = new MyAdapterMinuman(userMinumanActivity.this,dataListMinuman);
        recyclerViewMinuman.setAdapter(adapterMinuman);

        databaseReferenceMinuman = FirebaseDatabase.getInstance().getReference("Minuman");
        dialog.show();

        eventListener = databaseReferenceMinuman.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataListMinuman.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClassMinuman dataClassMinuman = itemSnapshot.getValue(DataClassMinuman.class);
                    dataListMinuman.add(dataClassMinuman);
                }
                adapterMinuman.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });
    }

    private void searchList(String text) {
        ArrayList<DataClassMinuman> searchList = new ArrayList<>();
        for(DataClassMinuman dataClassMinuman : dataListMinuman){
            if(dataClassMinuman.getDataTitleMinuman().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClassMinuman);
            }
        }
        adapterMinuman.searchDataList(searchList);
    }
}