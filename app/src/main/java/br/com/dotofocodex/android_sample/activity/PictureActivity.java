package br.com.dotofocodex.android_sample.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.com.dotofocodex.android_sample.R;
import br.com.dotofocodex.android_sample.glide.GlideApp;

public class PictureActivity extends AppCompatActivity {

    private static final String TAG = "PictureActivity";
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    private LinearLayout main;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        this.main = findViewById(R.id.ll_activity_picture);
        this.iv = findViewById(R.id.iv_activity_picture_taken);;

        if (!isWriteExternalPermissionGranted()) {
            requestWriteExternalPermission();
            return;
        }

        handleIntent();
    }

    private void handleIntent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String bitmap = extras.getString("image");
            if (bitmap == null) {
                Toast.makeText(this, "No image taken", Toast.LENGTH_SHORT).show();
                return;
            }

            GlideApp
                .with(this)
                .load(getFilesDir().getAbsolutePath().concat(File.separator).concat(bitmap))
                .into(iv);
        }
    }

    public boolean saveImage(Bitmap bitmap) {
        try {
            Bitmap rotatedBitmap = rotateImage(bitmap, -90);
            this.iv.setImageBitmap(rotatedBitmap);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream(rotatedBitmap.getByteCount());
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            String filename = System.nanoTime() + ".png";
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(bytes.toByteArray());
            fos.close();

            MediaStore.Images.Media.insertImage(getContentResolver(), rotatedBitmap, null, null);
            Log.d("TAG", "File Saved: " + filename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private boolean isWriteExternalPermissionGranted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestWriteExternalPermission() {
        Log.i(TAG, "WRITE_EXTERNAL_STORAGE permission has NOT been granted. Requesting permission.");
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i(TAG,"Displaying WRITE_EXTERNAL_STORAGE permission rationale to provide additional context.");
            Snackbar
                .make(main, "Por favor, é necessário a permissão para acessar a o armazenamento do dispositivo.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", (View view) -> ActivityCompat.requestPermissions(PictureActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE))
                    .show();
        }
        else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_EXTERNAL_STORAGE) {
            // Received permission result for WRITE_EXTERNAL_STORAGE permission.
            Log.i(TAG, "Received response for WRITE_EXTERNAL_STORAGE permission request.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // WRITE_EXTERNAL_STORAGE permission has been granted, preview can be displayed
                Log.i(TAG, "WRITE_EXTERNAL_STORAGE permission has now been granted. Showing preview.");
                Snackbar
                    .make(main, "Permissão cencedida!", Snackbar.LENGTH_SHORT)
                    .show();
                handleIntent();
            } else {
                Log.i(TAG, "WRITE_EXTERNAL_STORAGE permission was NOT granted.");
                Snackbar
                    .make(main, "Permissão negada", Snackbar.LENGTH_SHORT)
                    .show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
