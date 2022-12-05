package com.proyectA.NearMe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class All_Maps extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase db;
    String Lat ="", Longi="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_maps);
        //
        setTitle("Ubicacion de evento") ;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapas);
        mapFragment.getMapAsync(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.map_menu, menu);
        return true;
    }
    //Envia los datos recibidos del mapa, devuelta a la actividad crear evento
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.done_map:
                if (Lat.equals("") || Longi.equals("")){
                    Toast.makeText(All_Maps.this,"No se ha seleccionado una ubicacion",Toast.LENGTH_SHORT);
                }else{
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("lati",Lat);
                    resultIntent.putExtra("longi",Longi);
                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.clear();
        LatLng La_Serena = new LatLng(-29.90453, -71.24894); //-29.90453 -71.24894
        googleMap.addMarker(new MarkerOptions().position(La_Serena).title("Marker in La Serena"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(La_Serena));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.animateCamera( CameraUpdateFactory.zoomTo( 16.0f ) );

        // Setting a click event handler for the map
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

                // Clears the previously touched position
                googleMap.clear();

                // Animating to the touched position
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                googleMap.addMarker(markerOptions);
                //UBICACION FINAL
                Lat = String.valueOf(latLng.latitude);
                Longi = String.valueOf(latLng.longitude);
            }
        });
    }
}