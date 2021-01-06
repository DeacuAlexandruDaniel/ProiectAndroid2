package com.example.proiectandroid;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MuseumsMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,(WindowManager.LayoutParams.FLAG_FULLSCREEN));
        setContentView(R.layout.activity_museums_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng louvre = new LatLng(48.8606, 2.3376);
        LatLng metropolitanMuseumOfArt = new LatLng(40.7794, -73.9632);
        LatLng theNationalGallery = new LatLng(51.5089, -0.1283);
        LatLng theNationalArtCenter = new LatLng(35.6653, 139.7264);
        mMap.addMarker(new MarkerOptions().position(louvre).title("French Musée du Louvre"));
        mMap.addMarker(new MarkerOptions().position(metropolitanMuseumOfArt).title("The Metropolitan Museum of Art"));
        mMap.addMarker(new MarkerOptions().position(theNationalGallery).title("The National Gallery"));
        mMap.addMarker(new MarkerOptions().position(theNationalArtCenter).title("The National Art Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(louvre, 10f));
    }
}