package com.example.neilpelow.memoapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationLoc extends AppCompatActivity implements LocationListener {

  private TextView locationText, addressText;
  private LocationManager locationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location_loc);

    locationText = (TextView) findViewById(R.id.locationText);
    addressText = (TextView) findViewById(R.id.addressText);
    Log.w("running onCreate", "");
    setUpLocation();
  }

  public void onLocationChanged(Location location) {
    String latestLocation = "";
    if (location != null) {
      getAddress(location.getLongitude(), location.getLatitude());
      Log.w("onLocationChanged runs", " ");
      latestLocation = String.format("Current Location \n Longitute: %1$s \n Latitude: %2$s",
          location.getLongitude(), location.getLatitude());
    }
    Toast.makeText(LocationLoc.this, latestLocation, Toast.LENGTH_LONG).show();
    locationText.setText(latestLocation);
  }

  public void onProviderDisabled(String provider) {
    //Code
  }

  public void onProviderEnabled(String provider) {
    //Code
  }

  public void onStatusChanged(String provider, int status, Bundle extras) {
    //Code
  }

  public void setUpLocation() {
    try {
      Log.w("setUpLocation runs", " ");
      locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
      locationManager.requestLocationUpdates(
          LocationManager.GPS_PROVIDER,
          500,
          1,
          this);

    } catch (SecurityException e) {
      Log.d("MyLocation :", "u dun fkd up", e);
    }
  }

  //
  @Override
  protected void onResume() {
    super.onResume();
    try {
      Log.w("Tracking resumed", " ");
      locationManager.requestLocationUpdates(
          LocationManager.GPS_PROVIDER,
          500,
          1,
          this);
    } catch (SecurityException e) {
      Log.d("MyLocation :", "u dun fkd up", e);
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    try {
      Log.w("Tracking paused", " ");
      locationManager.removeUpdates(this);
    } catch (SecurityException e) {
      Log.d("MyLocation :", "u dun fkd up", e);
    }

  }

  public void getAddress(double longitude, double latitude) {

    if (longitude != 0.0 && latitude != 0.0) {

      Geocoder geocoder = new Geocoder(this, Locale.getDefault());

      String errorMessage = "";

      List<Address> addresses;

      try {
        addresses = geocoder.getFromLocation(
            latitude,
            longitude,
            1);

        try {

          String addressLine = addresses.get(0).getAddressLine(0);
          String city = addresses.get(0).getLocality();
          String country = addresses.get(0).getCountryName();
          //String county = addresses.get(0).getSubAdminArea();

          addressText.setText(addressLine + "\n" + city + "\n" + country);

        } catch (IndexOutOfBoundsException e) {
          e.printStackTrace();
        }

      } catch (IOException ioExcepion) {
        errorMessage = getString(R.string.no_address_found);
        Log.w("io Exception", " ");
      }
    }
  }
}//end MyLocation

