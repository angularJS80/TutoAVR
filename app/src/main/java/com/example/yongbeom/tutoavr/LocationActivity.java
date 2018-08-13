package com.example.yongbeom.tutoavr;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity {


    LocationManager locationManager;
    LocationListener locationListener;
    String locationProvider;
    private static final int REQUEST_FINE_LOCATION = 1;
    private static final int REQUEST_COARSE_LOCATION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_location);

        defineView();
        defineLocationSetting();

        //startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

    }



    private void defineLocationSetting() {
        // 좌표관리자 변수 선언 어플리케이션 단에서 제공하는 시스템 서비스를 메니저형태로 정의한다.
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // 로케이션 메지져는 감시자가 필요 한데 감시자가 하는일은 좌표 관련된 상태 이벤트를 감지 하고 해당 하는 이벤트에 맞는 처리를 구현 하면 된다.
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                Toast.makeText(LocationActivity.this, "longtitude=" + lng + ", latitude=" + lat, Toast.LENGTH_SHORT).show();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText(LocationActivity.this, "onStatusChanged=" + status , Toast.LENGTH_SHORT).show();
            }

            public void onProviderEnabled(String provider) {

                Toast.makeText(LocationActivity.this, "onProviderEnabled=" + provider , Toast.LENGTH_SHORT).show();
            }

            public void onProviderDisabled(String provider) {
                Toast.makeText(LocationActivity.this, "onProviderDisabled=" + provider , Toast.LENGTH_SHORT).show();
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            checkPermissionNProvider();
        } else {
            locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);
        }


    }

    private void defineView() {
        Button btnCheckLocation = findViewById(R.id.btn_check_location);
        btnCheckLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMyLocation();
            }
        });
    }


    private void getMyLocation() {
        Location currentLocation;
        if (locationProvider != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                checkPermissionNProvider();
            }else{
                currentLocation = locationManager.getLastKnownLocation(locationProvider);
                if (currentLocation != null) {
                    double lng = currentLocation.getLongitude();
                    double lat = currentLocation.getLatitude();
                    Toast.makeText(LocationActivity.this, "longtitude=" + lng + ", latitude=" + lat, Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(LocationActivity.this, "위치제공자가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissionNProvider() {
        Location currentLocation = null;
        locationProvider = null;
        int accessFineLocation = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (accessFineLocation == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
        }

        int accessCoarseLocation = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        if (accessCoarseLocation == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COARSE_LOCATION);
        }
        if (accessCoarseLocation == PackageManager.PERMISSION_GRANTED) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }

        if (accessFineLocation == PackageManager.PERMISSION_GRANTED) {
            locationProvider = LocationManager.GPS_PROVIDER;
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_COARSE_LOCATION:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(LocationActivity.this, " permission granted!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LocationActivity.this, " permission denied!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                break;
            case REQUEST_FINE_LOCATION:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    int grantResult = grantResults[i];
                    if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                        if(grantResult == PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(LocationActivity.this, " permission granted!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LocationActivity.this, " permission denied!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
                break;

        }
    }


}
