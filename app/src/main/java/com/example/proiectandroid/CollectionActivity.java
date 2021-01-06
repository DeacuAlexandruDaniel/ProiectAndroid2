package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    private AppDb database;

    ListView collectionList;
    Button btnDeleteCollection, btnDeleteItem;
    EditText editTxtIdToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_collection);

        collectionList = findViewById(R.id.collecitonList);

        final ArrayList<ArtObject> arrayList = new ArrayList<>();
        database = Room.databaseBuilder(this, AppDb.class, "artObjectsDB").allowMainThreadQueries().build();
        List<ArtObject> artObjects = database.ArtObjectDAO().getCollection(FirebaseAuth.getInstance().getUid().toString());
        for(ArtObject obj : artObjects){
            arrayList.add(obj);
        }

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        collectionList.setAdapter(arrayAdapter);

        btnDeleteCollection = findViewById(R.id.btnDeleteCollection);
        btnDeleteItem = findViewById(R.id.btnDeleteItem);
        //editTxtIdToDelete = findViewById(R.id.editTxtIdToDelete);

        btnDeleteCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database.ArtObjectDAO().deleteAll();
                new deleteAllAsyncQuerry().execute();
                arrayAdapter.clear();
                arrayAdapter.addAll(arrayList);
                arrayAdapter.notifyDataSetChanged();
            }
        });

        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int idToDelete = Integer.parseInt(editTxtIdToDelete.getText().toString());
                //database.ArtObjectDAO().deleteByID(idToDelete);
                new deleteByIdAsyncQuerry().execute();
               // editTxtIdToDelete.getText().clear();
                arrayAdapter.clear();
                arrayAdapter.addAll(arrayList);
                arrayAdapter.notifyDataSetChanged();
            }
        });

    }

    private class deleteByIdAsyncQuerry extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            editTxtIdToDelete = findViewById(R.id.editTxtIdToDelete);
            int idToDelete = Integer.parseInt(editTxtIdToDelete.getText().toString());
            database.ArtObjectDAO().deleteByID(idToDelete);
            editTxtIdToDelete.getText().clear();
            return null;
        }
    }

    private class deleteAllAsyncQuerry extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.ArtObjectDAO().deleteAll();
            return null;
        }
    }

}