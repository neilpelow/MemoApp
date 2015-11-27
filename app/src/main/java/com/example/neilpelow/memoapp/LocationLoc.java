package com.example.neilpelow.memoapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationLoc implements LocationListener {

  private LocationManager locationManager;
  private Context context;
  private String locationAddress;
  private Location location;

  public LocationLoc(Context context) {
    this.context = context;
    setUpLocation();
  }

  public void onLocationChanged(Location location) {

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
      locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      locationManager.requestLocationUpdates(
          LocationManager.GPS_PROVIDER,
          500,
          1,
          this);

      location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

      getAddress(location.getLongitude(), location.getLatitude());

    } catch (SecurityException e) {
      Log.d("MyLocation :", "Things aren't going well. Keep calm and carry on!", e);
    }
  }

  public String getLocationAddress() {

    if(location == null) {
      return "";
    } else {
      return locationAddress;
    }
  }


  public void getAddress(double longitude, double latitude) {

    if (longitude != 0.0 && latitude != 0.0) {

      Geocoder geocoder = new Geocoder(context, Locale.getDefault());

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

          locationAddress = addressLine+","+city+","+country;

        } catch (IndexOutOfBoundsException e) {
          e.printStackTrace();
        }

      } catch (IOException ioExcepion) {
        ioExcepion.printStackTrace();
      }
    }
  }
}//end MyLocation

