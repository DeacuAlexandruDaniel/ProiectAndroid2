package com.example.proiectandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "artObjects")
public class ArtObject {

    @Ignore
    private static int numberOfObjects = 0; // numarul obiectelor din colectie

    @PrimaryKey
    @NonNull
    public String user_id; // id-ul userului

    @ColumnInfo(index = true)
    public int object_id; // id-ul obiectului
    @ColumnInfo(name = "authenticity")
    public boolean authenticity; //autenticitatea obiectului (true-original/false-reproducere)
    @ColumnInfo(name = "isHighlight")
    public boolean isHighlight; // true-e considerata o piesa de rezistenta a colectiei
    @ColumnInfo(name = "price")
    public int price; // pretul actual
    @ColumnInfo(name = "year")
    public int year; // anul in care a fost finalizat
    @ColumnInfo(name = "nameOfTheArtObject")
    public String name; // numele obiectului
    @ColumnInfo(name = "typeOfTheArtObject")
    public String type; // tipul obiectului (ex: pictura, sculptura)
    @ColumnInfo(name = "nameOfTheAuthor")
    public String author; // numele autorului operei
    @ColumnInfo(name = "currentOfTheArtObject")
    public String current; // curentul din care face parte

    public ArtObject(boolean authenticity, boolean isHighlight, int price, int year, String name, String type, String author, String current, String user_id) {
        this.authenticity = authenticity;
        this.isHighlight = isHighlight;
        this.price = price;
        this.year = year;
        this.name = name;
        this.type = type;
        this.author = author;
        this.current = current;
        this.user_id = user_id;
        ++numberOfObjects;
    }

    @Override
    public String toString() {
        return "Art Object:" +
                "object id is " + object_id +
                " and it's authenticity is " + authenticity +
                ". The object is a highlight (" + isHighlight +
                ") and it's price is " + price +
                "$. It was made in the year " + year +
                ". The name of the piece is " + name +
                " and it's a " + type +
                " by " + author +
                ". The piece belongs to the " + current +
                " current";
    }
}