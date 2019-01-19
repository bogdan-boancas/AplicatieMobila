package bogdan.mobileapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;


public class ThirdActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
private static final int REQUEST_CAMERA=1;
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
/*verificam permisiile camerei*/
        setContentView(scannerView);
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            if(checkPermission()){
                Toast.makeText(ThirdActivity.this, "Permisie acceptata!", Toast.LENGTH_LONG).show();
            }
            else{
                requestPermissions();
            }
        }
    }

        private boolean checkPermission(){
            return (ContextCompat.checkSelfPermission(ThirdActivity.this, CAMERA)== PackageManager.PERMISSION_GRANTED);
        }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permission[], int grantResult[]){
        switch(requestCode){
            case REQUEST_CAMERA:
                if(grantResult.length>0){
                    boolean cameraAccepted = grantResult[0] ==PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(ThirdActivity.this, "Permisie acceptata!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(ThirdActivity.this, "Permisia nu a fost acceptata!", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("Trebuie aprobate ambele permisii",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }

                }
            break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
           if(checkPermission()){
               if(scannerView==null){
                   scannerView = new ZXingScannerView(this);
                   setContentView(scannerView);
               }
               scannerView.setResultHandler(this);
               scannerView.startCamera();
           }
           else{
               requestPermissions();
           }
       }
    }
    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(ThirdActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Anulare", null)
                .create()
                .show();

    }




    @Override
    public void handleResult(Result result) {

    }
}
