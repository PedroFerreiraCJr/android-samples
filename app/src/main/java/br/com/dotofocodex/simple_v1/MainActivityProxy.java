package br.com.dotofocodex.simple_v1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivityProxy extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_proxy);
        findViewById(R.id.proxy_button_1).setOnClickListener(new ProxyFactory.OnClickLoggingListener(new ProxyFactory.OnClickTimestampListener(v -> Toast.makeText(MainActivityProxy.this, "Hello World from Proxy!", Toast.LENGTH_SHORT).show())));
    }
}
