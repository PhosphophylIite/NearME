package com.proyectA.NearMe;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyectA.NearMe.Modelo.Evento;

import java.util.Map;

public class Maps extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static  final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    MarkerOptions markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //FIRE BASE
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            OnGPS();
        }else{
            getLocation();
        }
    }
    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(Maps.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Maps.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetWork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationGPS != null){
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();

            }
            if (locationNetWork != null){
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();

            }
            if (locationPassive != null){
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
            }
            else
            {
                Toast.makeText(this,"No puedo tener tu dispositivo",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void OnGPS(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Prende el GPS").setCancelable(false).setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent((Settings.ACTION_LOCATION_SOURCE_SETTINGS)));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Evento evento = dataSnapshot.getValue(Evento.class);
                    LatLng latLng = new LatLng(Double.valueOf(evento.getLatitud()), Double.valueOf(evento.getLongitud()));

                    markerOptions = new MarkerOptions();
                    markerOptions.position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    googleMap.addMarker(markerOptions).setTitle(evento.getNombre());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LatLng La_Serena = new LatLng(-29.90453, -71.24894); //-29.90453 -71.24894
        googleMap.addMarker(new MarkerOptions().position(La_Serena).title("Marker in La Serena"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(La_Serena));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 16.0f ) );

        googleMap.setOnMarkerClickListener(this);

    }
    //POSIBLE IMPLEMENTACION
    /*
        Cuando se presione el marcador lleva directamente al detalle del evento
     */
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(Maps.this, marker.getTitle(), Toast.LENGTH_LONG).show();
        return true;
    }
}