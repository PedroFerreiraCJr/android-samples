package br.com.dotofocodex.android_sample.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

import br.com.dotofocodex.android_sample.R;

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";
    private static final int REQUEST_CAMERA = 0;

    public static Bitmap bitmap;

    private Camera mCamera;
    private CameraPreview mPreview;
    private Camera.PictureCallback mPicture;
    private Button capture;
    private Button switchCamera;
    private Context myContext;
    private LinearLayout cameraPreview;
    private LinearLayout main;
    private boolean cameraFront = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        myContext = this;
        main = findViewById(R.id.ll_activity_camera);

        if (!isCameraPermissionGranted()) {
            // Camera permission has not been granted.
            requestCameraPermission();
        }

        cameraPreview = findViewById(R.id.ll_activity_camera_preview);
        mPreview = new CameraPreview(myContext, mCamera);
        cameraPreview.addView(mPreview);

        capture = findViewById(R.id.bt_activity_camera_cam);
        capture.setOnClickListener((View v) -> mCamera.takePicture(null, null, mPicture));

        switchCamera = findViewById(R.id.bt_activity_camera_switch);
        switchCamera.setOnClickListener((View v) -> {
            //get the number of cameras
            int camerasNumber = Camera.getNumberOfCameras();
            if (camerasNumber > 1) {
                //release the old camera instance
                //switch camera, from the front and the back and vice versa
                releaseCamera();
                chooseCamera();
            }
        });
    }

    private boolean isCameraPermissionGranted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void displayCamera() {
        this.mCamera = Camera.open();
        this.mPreview.setCamera(this.mCamera);
        this.mCamera.setDisplayOrientation(90);
        this.mCamera.startPreview();
    }

    private void requestCameraPermission() {
        Log.i(TAG, "CAMERA permission has NOT been granted. Requesting permission.");
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i(TAG,"Displaying camera permission rationale to provide additional context.");
            Snackbar
                .make(main, "Por favor, é necessário a permissão para acessar a camera do dispositivo",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", (View view) -> ActivityCompat.requestPermissions(CameraActivity.this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA))
                    .show();
        }
        else {
            // Camera permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }

        return cameraId;
    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        //Search for the back facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;
            }
        }

        return cameraId;
    }

    public void chooseCamera() {
        //if the camera preview is the front
        if (cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera, null);
            }
        }
        else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                //open the backFacingCamera
                //set a picture callback
                //refresh the preview
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(90);
                mPicture = getPictureCallback();
                mPreview.refreshCamera(mCamera, null);
            }
        }
    }

    private void releaseCamera() {
        // stop and release camera
        Log.d(TAG, "releaseCamera...");
        if (this.mCamera != null) {
            this.mCamera.stopPreview();
            this.mCamera.setPreviewCallback(null);
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    private Camera.PictureCallback getPictureCallback() {
        Camera.PictureCallback picture = (byte[] data, Camera camera) -> {
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Intent intent = new Intent(CameraActivity.this, PictureActivity.class);
            intent.getExtras().putParcelable("image", bitmap);
            startActivity(intent);
        };

        return picture;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
        if (isCameraPermissionGranted()) {
            displayCamera();
            /*
            if (this.mCamera == null) {
                this.mCamera = Camera.open();
                this.mCamera.setDisplayOrientation(90);
                this.mPicture = getPictureCallback();
                this.mPreview.refreshCamera(this.mCamera, null);
            }
            */
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //when on Pause, release camera in order to be used from other applications
        releaseCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA) {
            // Received permission result for camera permission.
            Log.i(TAG, "Received response for Camera permission request.");
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission has been granted, preview can be displayed
                Log.i(TAG, "CAMERA permission has now been granted. Showing preview.");
                Snackbar
                    .make(main, "Permissão cencedida!", Snackbar.LENGTH_SHORT)
                        .show();
                displayCamera();
            } else {
                Log.i(TAG, "CAMERA permission was NOT granted.");
                Snackbar
                    .make(main, "Permissão negada", Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public static class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

        private SurfaceHolder holder;
        private Camera camera;

        public CameraPreview(Context context, Camera camera) {
            super(context);
            this.camera = camera;
            this.holder = getHolder();
            this.holder.addCallback(this);
            // deprecated API
            //this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void setCamera(Camera camera) {
            this.camera = camera;
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (this.camera != null) {
                try {
                    this.camera.setPreviewDisplay(holder);
                    this.camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            refreshCamera(this.camera, holder);
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (this.camera != null) {
                this.camera.release();
            }
        }

        private void refreshCamera(Camera camera, SurfaceHolder holder) {
            if (holder != null) {
                this.holder = holder;
            }

            if (this.holder.getSurface() == null) {
                return;
            }

            if (this.camera != null) {
                this.camera.stopPreview();
                setCamera(camera);

                try {
                    this.camera.setPreviewDisplay(this.holder);
                    this.camera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}