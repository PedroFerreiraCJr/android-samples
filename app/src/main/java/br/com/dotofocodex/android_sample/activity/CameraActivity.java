package br.com.dotofocodex.android_sample.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import br.com.dotofocodex.android_sample.R;


/***
 * More information on:
 *  https://developer.android.com/guide/topics/media/camera.html
 */
public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";
    private static final int REQUEST_CAMERA = 0;

    private Camera mCamera;
    private CameraPreviewSurfaceView mPreview;
    private Camera.PictureCallback mPicture;
    private Button capture;
    private Button angle;
    private Button switchCamera;
    private LinearLayout cameraPreview;
    private LinearLayout main;
    private boolean cameraFront = false;
    private int backFacingCameraId;
    private int angleValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate...");
        setContentView(R.layout.activity_camera);

        this.angleValue = 0;
        this.backFacingCameraId = findBackFacingCamera();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.main = findViewById(R.id.ll_activity_camera);

        if (!isCameraPermissionGranted()) {
            // Camera permission has not been granted.
            requestCameraPermission();
        }

        this.cameraPreview = findViewById(R.id.ll_activity_camera_preview);
        this.mPreview = new CameraPreviewSurfaceView(this);
        this.cameraPreview.addView(this.mPreview);

        this.capture = findViewById(R.id.bt_activity_camera_cam);
        this.capture.setOnClickListener((View v) -> takePicture());

        this.angle = findViewById(R.id.bt_activity_camera_angle);
        this.angle.setOnClickListener((View v) -> {
            if (this.angleValue == 0) {
                this.angleValue = 90;
                this.angle.setText("Angle 90");
                return;
            }

            if (this.angleValue == 90) {
                this.angleValue = 180;
                this.angle.setText("Angle 180");
                return;
            }

            if (this.angleValue == 180) {
                this.angleValue = 270;
                this.angle.setText("Angle 270");
                return;
            }

            if (this.angleValue == 270) {
                this.angleValue = 360;
                this.angle.setText("Angle 360");
                return;
            }

            if (this.angleValue == 360) {
                this.angleValue = 0;
                this.angle.setText("Angle 0");
                return;
            }

        });

        this.switchCamera = findViewById(R.id.bt_activity_camera_switch);
        this.switchCamera.setOnClickListener((View v) -> {
            //get the number of cameras
            int cameras = Camera.getNumberOfCameras();
            if (cameras > 1) {
                //release the old camera instance
                //switch camera, from the front and the back and vice versa
                Log.d(TAG, "switching cameras...");
                chooseCamera();
            }
        });

        this.mPicture = getPictureCallback();
    }

    private boolean isCameraPermissionGranted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
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

    private void takePicture() {
        this.mCamera.takePicture(null, null, this.mPicture);
    }

    private void displayCamera(int id) {
        Log.d(TAG, "displayCamera[" + id + "]...");
        releaseCamera();
        this.mCamera = Camera.open(id);
        applyParameters();
        this.mPreview.refreshCamera(this.mCamera);
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
                this.cameraFront = true;
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
                this.cameraFront = false;
                break;
            }
        }

        return cameraId;
    }

    public void chooseCamera() {
        if (this.cameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                displayCamera(cameraId);
            }
        }
        else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                displayCamera(cameraId);
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
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Bitmap rotatedBitmap = rotateImage(bitmap, this.angleValue);
            //Bitmap rotatedBitmap = rotateImage(bitmap, -90);
            //Bitmap rotatedBitmap = rotateImage(bitmap, 90);
            //Bitmap rotatedBitmap = rotateImage(bitmap, 180);
            //Bitmap rotatedBitmap = rotateImage(bitmap, 270);
            //Bitmap rotatedBitmap = rotateImage(bitmap, 360);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream(rotatedBitmap.getByteCount());
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

            try {
                String filename = System.nanoTime() + ".jpeg";
                FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                fos.write(bytes.toByteArray());
                fos.close();

                MediaStore.Images.Media.insertImage(getContentResolver(), rotatedBitmap, null, null);

                Intent intent = new Intent(CameraActivity.this, PictureActivity.class);
                intent.putExtra("image", filename);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        return picture;
    }

    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    private void applyParameters() {
            /*
            Log.d(TAG, "applyParameters: parameters.size " + sizes.size());
            Log.d(TAG, "applyParameters: " + parameters.getAntibanding());
            Log.d(TAG, "applyParameters: " + parameters.getColorEffect());
            Log.d(TAG, "applyParameters: " + parameters.getExposureCompensation());
            Log.d(TAG, "applyParameters: " + parameters.getFlashMode());
            Log.d(TAG, "applyParameters: " + parameters.getFocusMode());
            Log.d(TAG, "applyParameters: " + parameters.getJpegQuality());
            Log.d(TAG, "applyParameters: " + parameters.getJpegThumbnailQuality());
            Log.d(TAG, "applyParameters: " + parameters.getJpegThumbnailSize());
            Log.d(TAG, "applyParameters: " + parameters.getMaxZoom());
            Log.d(TAG, "applyParameters: " + parameters.getPictureFormat());
            Log.d(TAG, "applyParameters: " + parameters.getPreferredPreviewSizeForVideo());
            Log.d(TAG, "applyParameters: " + parameters.getPictureSize());
            Log.d(TAG, "applyParameters: " + parameters.getPreviewFormat());
            Log.d(TAG, "applyParameters: " + parameters.getPreviewSize());
            Log.d(TAG, "applyParameters: " + parameters.getSceneMode());

            Camera.Size size;
            for (int i=0; i<sizes.size(); i++) {
                size = sizes.get(i);
                Log.d(TAG, "applyParameters: choose: " + size.width + ", " + size.height);
                try {
                    parameters.setPictureSize(size.width, size.height);
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                    parameters.setJpegQuality(100);
                    this.camera.setParameters(parameters);
                    Log.d(TAG, "applyParameters: size successfully applied: " + size.width + ", " + size.height);
                } catch (Exception e) {
                    Log.d(TAG, "applyParameters: error on size: " + size.width + ", " + size.height);
                    e.printStackTrace();
                }
                if(sizes.get(i).width > size.width)
                    size = sizes.get(i);
            }
            */

        Camera.Parameters parameters = this.mCamera.getParameters();
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();

        Camera.Size size = sizes.get(0);
        for (int i=0; i<sizes.size(); i++) {
            if(sizes.get(i).width > size.width) {
                size = sizes.get(i);
            }
        }

        Log.d(TAG, "applyParameters last size: " + size.width + ", " + size.height);

        parameters.setPictureSize(size.width, size.height);
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        parameters.setJpegQuality(100);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        if (display.getRotation() == Surface.ROTATION_0) {
            Log.d(TAG, "applyParameters: rotation 0");
            this.mCamera.setDisplayOrientation(90);
        }
        else if (display.getRotation() == Surface.ROTATION_270) {
            Log.d(TAG, "applyParameters: rotation 270");
            this.mCamera.setDisplayOrientation(180);
        }

        this.mCamera.getParameters().set("orientation", "portrait");
        this.mCamera.setParameters(parameters);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume...");
        if (isCameraPermissionGranted()) {
            displayCamera(this.backFacingCameraId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause... ");
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
                displayCamera(this.backFacingCameraId);
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

    public static class CameraPreviewSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        private Camera camera;

        public CameraPreviewSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            // deprecated API
            //this.holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }

        public void setCamera(Camera camera) {
            Log.d(TAG, "setCamera...");
            this.camera = camera;
        }

        private void refreshCamera() {
            refreshCamera(this.camera, true);
        }

        private void refreshCamera(Camera camera) {
            refreshCamera(camera, false);
        }

        private void refreshCamera(Camera camera, boolean force) {
            Log.d(TAG, "refreshCamera...");
            if (getHolder().getSurface() == null) {
                return;
            }

            if (force || this.camera == null || !this.camera.equals(camera)) {
                if (force) {
                    Log.d(TAG, "refreshCamera caused by force: true");
                }

                if (this.camera == null) {
                    Log.d(TAG, "refreshCamera caused by camera equal to null");
                }

                if (this.camera == null) {
                    Log.d(TAG, "refreshCamera caused by diff cameras");
                }

                setCamera(camera);

                try {
                    if (this.camera != null) {
                        this.camera.setPreviewDisplay(getHolder());
                        this.camera.setDisplayOrientation(90);
                        this.camera.startPreview();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.d(TAG, "surfaceCreated...");
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Log.d(TAG, "surfaceChanged...");
            refreshCamera();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.d(TAG, "surfaceDestroyed...");
            if (this.camera != null) {
                this.camera.release();
                this.camera = null;
            }
        }
    }

}