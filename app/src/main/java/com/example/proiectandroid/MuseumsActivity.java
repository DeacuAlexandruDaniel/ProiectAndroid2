package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MuseumsActivity extends AppCompatActivity {

    public ArrayList<Museum> museumsList = new ArrayList<Museum>();
    Button btnMap;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_museums);

        btnMap = findViewById(R.id.btnMuseumsMap);

        Resources res = getResources();
        String[] allMuseums = res.getStringArray(R.array.museums);
        String[] allLocations = res.getStringArray(R.array.locations);
        Log.v("test", allMuseums[2]);
        Log.v("test", allLocations[2]);
        populateArrayList(allMuseums, allLocations);
        Log.v("test", museumsList.get(2).toString());
        MuseumAdapter adapter = new MuseumAdapter(getApplicationContext(), R.layout.list_item, museumsList);
        for(Museum m : museumsList){
            adapter.add(m);
        }
        listView = (ListView)findViewById(R.id.lstViewMuseums);
        listView.setAdapter(adapter);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MuseumsActivity.this, MuseumsMapActivity.class));
            }
        });

    }

    public void populateArrayList(String[] museums, String[] locations){
        // Museum m = new Museum(museums[1], locations[2], R.drawable.louvre);
        museumsList.add(new Museum(museums[0], locations[0], R.drawable.louvre));
        museumsList.add(new Museum(museums[1], locations[1], R.drawable.metropolitan_museum_of_art));
        museumsList.add(new Museum(museums[2], locations[2], R.drawable.the_national_gallery));
        // museumsList.add(m);
        museumsList.add(new Museum(museums[3], locations[3], R.drawable.the_national_art_center));
    }
}