package br.com.dotofocodex.android_sample.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.dotofocodex.android_sample.R;

public class MobileDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_data);

        Button enable = findViewById(R.id.bt_activity_mobile_data_enable_mobile_data);
        enable.setOnClickListener((View view) -> {
            Toast.makeText(MobileDataActivity.this, "Não é mais possível habilitar os dados móveis.", Toast.LENGTH_SHORT).show();
        });

        Button disable = findViewById(R.id.bt_activity_mobile_data_disable_mobile_data);
        disable.setOnClickListener((View view) -> {
            Toast.makeText(MobileDataActivity.this, "Não é mais possível desabilitar os dados móveis.", Toast.LENGTH_SHORT).show();
        });

        Button enableWifi = findViewById(R.id.bt_activity_mobile_data_enable_wifi);
        enableWifi.setOnClickListener((View view) -> {
            setWifiState(true);
        });

        Button disableWifi = findViewById(R.id.bt_activity_mobile_data_disable_wifi);
        disableWifi.setOnClickListener((View view) -> {
            setWifiState(false);
        });
    }

    private void setWifiState(boolean enabled) {
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(enabled);
    }

}
