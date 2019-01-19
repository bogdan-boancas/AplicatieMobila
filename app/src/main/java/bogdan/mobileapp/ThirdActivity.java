package bogdan.mobileapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    @Override
    public void handleResult(Result result) {

    }
}
