package br.com.dotofocodex.simple_v1.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import br.com.dotofocodex.simple_v1.R;

public class HTMLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        String html = "<h2 class=\"align-center\">Title</h2><br><p class=\"align-center\" style=\"background-color: yellow; color: #000000; text-align: center;\">Description here</p>";

        TextView tv = findViewById(R.id.textview_html);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv.setText(Html.fromHtml(html));
        }
    }
}
