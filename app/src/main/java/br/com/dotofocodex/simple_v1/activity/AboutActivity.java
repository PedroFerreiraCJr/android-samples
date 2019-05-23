package br.com.dotofocodex.simple_v1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.dotofocodex.simple_v1.R;
import br.com.dotofocodex.simple_v1.component.MyMsgBox;

public class AboutActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings settings = map.getUiSettings();
        settings.setCompassEnabled(false);
        settings.setAllGesturesEnabled(true);
        settings.setRotateGesturesEnabled(false);
        settings.setScrollGesturesEnabled(true);
        settings.setZoomControlsEnabled(true);
        settings.setZoomGesturesEnabled(false);
        settings.setMyLocationButtonEnabled(false);
        settings.setIndoorLevelPickerEnabled(true);
        settings.setMapToolbarEnabled(true);

        LatLng sm = new LatLng(-3.8781768, -38.6127169);
        map.addMarker(new MarkerOptions()
            .position(sm)
            .title("Só Macarrão"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sm));
        map.moveCamera(CameraUpdateFactory.zoomTo(18.0f));
    }
}
