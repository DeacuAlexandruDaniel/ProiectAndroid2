package com.example.proiectandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MuseumAdapter extends ArrayAdapter<Museum> {
    private ArrayList<Museum> museumList;

    public MuseumAdapter(@NonNull Context context, int resource, ArrayList<Museum> museumList) {
        super(context, resource);
        this.museumList = museumList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int phraseIndex = position;
        Museum museum = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        ImageView museumImage = convertView.findViewById(R.id.museum_imageview);
        TextView museumName = convertView.findViewById(R.id.txtViewMuseumName);
        TextView museumLocation = convertView.findViewById(R.id.txtViewMuseumLocation);

        museumImage.setImageResource(museumList.get(position).getMuseumImageId());
        museumName.setText(museumList.get(position).getMuseumName());
        museumLocation.setText(museumList.get(position).getMuseumLocation());
        return convertView;
    }
}
