package jp.arkw.localorbis;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import android.Manifest;

public class MainActivity extends AppCompatActivity implements LocationListener {
    LocationManager locationManager;
    private float speed = 0f;
    private final Point orbis = new Point("ループコイル式Hシステム", 34.917239, 137.211372, 50);

    private ImageView imageView;
    private TextView textViewPosition;
    private TextView textViewDistance;
    private TextView textViewName;

    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            locationStart();
        } else {
            Toast toast = Toast.makeText(this, "端末の設定から本アプリのGPS使用を許可してください。", Toast.LENGTH_SHORT);
            toast.show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            locationStart();
        }
        imageView = findViewById(R.id.image_view);
        textViewPosition = findViewById(R.id.text_view_position);
        textViewDistance = findViewById(R.id.text_view_distance);
        textViewName = findViewById(R.id.text_view_name);
        textViewPosition.setText("緯度: - / 経度: -\n現在速度: - km/h");
    }

    private void locationStart(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
        } else {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location.hasSpeed()) {
            speed = location.getSpeed() * 3.6f;
        } else {
            speed = 0f;
        }
        final double latitude = location.getLatitude();
        final double longitude = location.getLongitude();
        textViewPosition.setText("緯度: " + latitude + " / 経度: " + longitude + "\n現在速度: " + (int) speed + " km/h");
        int distance = (int) getDistance(latitude, longitude, orbis.getLatitude(), orbis.getLongitude());
        textViewDistance.setText(distance + " m");
        textViewName.setText(orbis.getName());
        switch (orbis.getLimit()) {
            case 50:
                imageView.setImageResource(R.drawable.limit_50);
                break;
            default:
                imageView.setImageResource(R.drawable.logo);
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    public static double getDistance(double latitudeA, double longitudeA, double latitudeB, double longitudeB) {
        Location locationA = new Location("Point A");
        locationA.setLatitude(latitudeA);
        locationA.setLongitude(longitudeA);
        Location locationB = new Location("Point B");
        locationB.setLatitude(latitudeB);
        locationB.setLongitude(longitudeB);
        double distance = locationA.distanceTo(locationB);
        return distance;
    }
}