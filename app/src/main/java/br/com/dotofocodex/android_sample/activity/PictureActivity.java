package br.com.dotofocodex.android_sample.activity;

import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.dotofocodex.android_sample.R;

public class PictureActivity extends AppCompatActivity {

    private static final String TAG = "PictureActivity";
    private static final String IMAGE_DIRECTORY = "/CustomImage";

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable bitmap = extras.getParcelable("image");
            if (bitmap == null) {
                Toast.makeText(this, "No image taken", Toast.LENGTH_SHORT).show();
                return;
            }

            if (bitmap instanceof Bitmap) {
                iv = findViewById(R.id.iv_activity_picture_taken);
                iv.setImageBitmap((Bitmap) bitmap);
                saveImage((Bitmap) bitmap);
                Toast.makeText(this, "Image saved!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);

        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            Log.d(TAG, "" + wallpaperDirectory.mkdirs());
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, System.nanoTime() + ".jpg");
            f.createNewFile();   //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this, new String[] {f.getPath()}, new String[] {"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
