package com.example.hong.mylifelogger;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by admin on 2016-11-28.
 */
public class SimpleMap extends Activity implements OnMapReadyCallback {
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    double latitude, longitude;
    String title, type;
    private GoogleMap googleMap;

    Button back;

    public void onMapReady(final GoogleMap map)  {
        googleMap = map;
        Intent intent = getIntent();
        latitude =  intent.getDoubleExtra("LATITUDE_KEY",0.00);
        longitude = intent.getDoubleExtra("LONGITUDE_KEY",0.00);
        type = intent.getStringExtra("TYPE_KEY");
        title = intent.getStringExtra("TITLE_KEY");
        LatLng tmp = new LatLng(latitude,longitude);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tmp, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
        map.addMarker(new MarkerOptions()
                .position(tmp)
                .title(type)
                .snippet(title));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplemap);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        back = (Button) findViewById(R.id.backbtn);
        back.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();

            }
        });






    }


}
