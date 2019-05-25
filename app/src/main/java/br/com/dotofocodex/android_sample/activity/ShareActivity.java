package br.com.dotofocodex.android_sample.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.dotofocodex.android_sample.R;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Button share = findViewById(R.id.bt_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
                    i.putExtra(Intent.EXTRA_TEXT, "http://www.url.com");
                    startActivity(Intent.createChooser(i, "Share URL"));
                */

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                i.putExtra(Intent.EXTRA_TEXT, "Your body is here");
                startActivity(Intent.createChooser(i, "Share using"));
            }
        });

        Button inst = findViewById(R.id.bt_instagram);
        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    Uri uri = Uri.parse("http://instagram.com/_u/xxx");
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                    likeIng.setPackage("com.instagram.android");

                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/xxx")));
                    }
                */

                Uri uri = Uri.parse("https://www.instagram.com/pedrojunior_2.7/?hl=pt-br");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.setPackage("com.instagram.android");

                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ShareActivity.this, "App Instagram not found", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/pedrojunior_2.7/?hl=pt-br")));
                }
            }
        });

        Button face = findViewById(R.id.bt_facebook);
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                    https://stackoverflow.com/questions/4810803/open-facebook-page-from-android-app
                    try {
                        context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                        return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/<id_here>"));
                    } catch (Exception e) {
                        return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/<user_name_here>"));
                    }
                */
                Uri uri = Uri.parse("https://pt-br.facebook.com/neymarjr/");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.setPackage("com.facebook.katana");

                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ShareActivity.this, "App Facebook not found", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://pt-br.facebook.com/neymarjr/")));
                }
            }
        });



        Button rate = findViewById(R.id.bt_rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*
                    Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    // To count with Play market backstack, After pressing back button,
                    // to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
                    }
                */

                //Uri uri = Uri.parse("market://details?id=" + ShareActivity.this.getPackageName());
                Uri uri = Uri.parse("market://details?id=br.com.dotofcodex.samplefcm");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(ShareActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + ShareActivity.this.getPackageName())));
                }
            }
        });
    }
}
