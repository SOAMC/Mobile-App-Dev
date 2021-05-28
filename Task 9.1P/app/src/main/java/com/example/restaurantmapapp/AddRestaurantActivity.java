package com.example.restaurantmapapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AddRestaurantActivity extends AppCompatActivity {
    private static final String TAG = "Running ";
    DatabaseHelper mapDb;
    LocationManager locationManager;
    LocationListener locationListener;
    Button B_currentLocation, B_showMap, B_save;
    EditText ET_placeName;
    String placeName;
    LatLng chosenLocation;
    Double lng, lat;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
            }
        }
    }

    public void location() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Boolean checkPlace(String name, LatLng coords) {
        if (name.equals("Place Name"))
        {
            Toast.makeText(this, "Please enter the location name first.", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (coords == null) {
            Toast.makeText(this, "Please choose a location first.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);
        B_currentLocation = findViewById(R.id.currentLocation_B);
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        B_showMap = findViewById(R.id.show_B_2);
        ET_placeName = findViewById(R.id.PlaceName_ET);
        B_save = findViewById(R.id.save_b);
        mapDb = new DatabaseHelper(this);

        Places.initialize(getApplicationContext(), getString(R.string.PlacesAPI));

        PlacesClient placesClient = Places.createClient(this);

        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
                chosenLocation = place.getLatLng();
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });

        B_currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location();
            }
        });

        B_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeName = ET_placeName.getText().toString();
                if (checkPlace(placeName, chosenLocation)) {
                    Intent intent = new Intent(AddRestaurantActivity.this, MapsActivity.class);
                    intent.putExtra("SOURCE", "single");
                    intent.putExtra("COORDS", chosenLocation);
                    intent.putExtra("NAME", placeName);
                    startActivity(intent);
                }
            }
        });

        B_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeName = ET_placeName.getText().toString();
                if (checkPlace(placeName, chosenLocation)) {
                    long result = mapDb.insertLocation(placeName, chosenLocation.latitude, chosenLocation.longitude);
                }
            }
        });

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lng = location.getLongitude();
                lat = location.getLatitude();
                chosenLocation = new LatLng(lat, lng);
                autocompleteFragment.setText(lat + ", " + lng);

            }
        };
    }
}