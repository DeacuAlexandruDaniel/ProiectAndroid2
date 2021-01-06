package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;


public class AddItemActivity extends AppCompatActivity {

    private AppDb database;

    FirebaseAuth mAuth;

    private EditText editTxtPieceName;
    private EditText editTxtAuthorName;
    private EditText editTxtYear;
    private EditText editTxtPrice;
    private Switch switchHighlight;
    private ToggleButton tglAuthenthicity;
    private Spinner spnCurrent;
    private Spinner spnType;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_add_item);

        database = Room.databaseBuilder(this, AppDb.class, "artObjectsDB").allowMainThreadQueries().build(); //.allowMainThreadQuerry() ???
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertArtObject();
                editTxtPrice.getText().clear();
                editTxtPieceName.getText().clear();
                editTxtAuthorName.getText().clear();
                editTxtYear.getText().clear();
            }
        });

    }

    private void insertArtObject(){
        editTxtPieceName = findViewById(R.id.editTxtPieceName);
        editTxtAuthorName = findViewById(R.id.editTxtAuthorName);
        editTxtYear = findViewById(R.id.editTxtYear);
        editTxtPrice = findViewById(R.id.editTxtPrice);
        switchHighlight = (Switch)findViewById(R.id.switchHighlight);
        tglAuthenthicity = (ToggleButton)findViewById(R.id.tglAuthenticity);
        spnCurrent = (Spinner)findViewById(R.id.spnCurrent);
        spnType = (Spinner)findViewById(R.id.spnType);
        String pieceName = editTxtPieceName.getText().toString();
        String authorName = editTxtAuthorName.getText().toString();
        int year = Integer.parseInt(editTxtYear.getText().toString());
        int price = Integer.parseInt(editTxtPrice.getText().toString());
        boolean highlight = switchHighlight.isChecked();
        boolean authenthicity = tglAuthenthicity.isChecked();
        String current = spnCurrent.getSelectedItem().toString();
        String type = spnType.getSelectedItem().toString();
        String user_id = FirebaseAuth.getInstance().getUid().toString();
        ArtObject obj = new ArtObject(authenthicity, highlight, price, year, pieceName, type, authorName, current, user_id);
        Log.v("test ", obj.toString());
        database.ArtObjectDAO().insertArtObject(obj);
        Log.v("test", "obiect introdus in baza de date");
        Toast.makeText(this, "Object added to collection", Toast.LENGTH_SHORT).show();
    }
}