package com.example.hong.mylifelogger;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * Created by admin on 2016-11-27.
 */
public class ManualMap  extends Activity implements OnMapReadyCallback, GoogleMap.OnMapClickListener , GoogleMap.OnMarkerClickListener{
    static final LatLng SEOUL = new LatLng(37.56, 126.97);
    Intent intent;
    GoogleMap googleMap;
    Button save;
    double latitude, longitude;
    Marker marker;
    LatLng pos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);


        save = (Button) findViewById(R.id.savebtn);
        findViewById(R.id.savebtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK, intent);
                finish();
            }
        });



    }

    public void onMapReady(final GoogleMap map) {
        googleMap = map;
        intent = getIntent();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        //map.addMarker(new MarkerOptions().position(SEOUL).title("SEOUL"));
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMarkerClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);

    }

    public void onMapClick (LatLng point) {
        googleMap.addMarker(new MarkerOptions()
                .position(point)
                .title("LongClick plz"));
        Toast.makeText(getApplicationContext(), "위치가 마크됨", Toast.LENGTH_SHORT).show();
        intent = getIntent();
        intent.putExtra("LATITUDE_KEY", point.latitude);
        intent.putExtra("LONGITUDE_KEY", point.longitude);


    }

     public boolean onMarkerClick (Marker marker){

        //finish();
        return true;
    }


}
