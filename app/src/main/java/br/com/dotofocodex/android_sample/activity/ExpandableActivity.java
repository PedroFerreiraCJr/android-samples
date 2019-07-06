package br.com.dotofocodex.android_sample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.dotofocodex.android_sample.App;
import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.util.ViewAnimation;


public class ExpandableActivity extends AppCompatActivity {

    private LinearLayout ll;
    private TextView tv;
    private Button bt;
    private boolean visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        this.visible = false;
        createAsync();
    }

    private void createAsync() {
        App.getInstance().getExecutorService().submit(() -> {
            init();
            bind();
        });
    }

    private void init() {
        this.ll = findViewById(R.id.ll_activity_expandable);
        this.tv = findViewById(R.id.tv_activity_expandable);
        this.bt = findViewById(R.id.bt_activity_expandable);
    }

    private void bind() {
        this.tv.setOnClickListener((View view) -> toggle());
        this.bt.setOnClickListener((View view) -> toggle());
    }

    private void toggle() {
        if (!this.visible) {
            ViewAnimation.expand(this.ll);
        }
        else {
            ViewAnimation.collapse(this.ll);
        }

        this.visible = !visible;
    }
}
