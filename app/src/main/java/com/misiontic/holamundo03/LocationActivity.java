package com.misiontic.holamundo03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.misiontic.holamundo03.location.Location;

public class LocationActivity extends AppCompatActivity {

    private TextView tv_latitude;
    private TextView tv_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tv_latitude = findViewById(R.id.tv_latitude);
        tv_longitude = findViewById(R.id.tv_longitude);

        Location newLocation = new Location(this, LocationActivity.this);
        tv_latitude.setText(newLocation.getLatitud());
        tv_longitude.setText(newLocation.getLongitud());

    }
}