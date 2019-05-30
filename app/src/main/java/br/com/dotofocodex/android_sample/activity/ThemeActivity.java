package br.com.dotofocodex.android_sample.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.dotofocodex.android_sample.R;

public class ThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("simple.v1", MODE_PRIVATE);
        String theme = sharedPreferences.getString("theme", "");
        if (!theme.isEmpty()) {
            if (theme.equals("1")) {
                setTheme(R.style.Theme_App_Lilac);
            }

            if (theme.equals("2")) {
                setTheme(R.style.Theme_App_Mint);
            }
        }

        setContentView(R.layout.activity_themes);

        Button theme1 = findViewById(R.id.change_theme_1);
        theme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("simple.v1", MODE_PRIVATE).edit();
                editor.putString("theme", "1");
                editor.apply();
                recreate();
            }
        });

        Button theme2 = findViewById(R.id.change_theme_2);
        theme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("simple.v1", MODE_PRIVATE).edit();
                editor.putString("theme", "2");
                editor.apply();
                recreate();
            }
        });
    }
}
