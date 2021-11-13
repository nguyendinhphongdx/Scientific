package com.example.scientificresearch.Ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.scientificresearch.Common.Functions;
import com.example.scientificresearch.R;
import com.huawei.agconnect.auth.AGConnectAuth;
import com.huawei.agconnect.auth.VerifyCodeResult;
import com.huawei.agconnect.auth.VerifyCodeSettings;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class VerifyOTP extends AppCompatActivity {
    private Button btnScan;
    private EditText txtToken;
    private static int REQUEST_CODE_SCAN_ONE = 1111;
    private static int DEFAULT_VIEW =0x22;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_opt);
        setViews();
        setUp();
        setListeners();
    }

    private void setUp() {
        txtToken.setTextSize(20);
        txtToken.setLetterSpacing(Float.parseFloat("0.1"));
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SendSMS();
                OpenCamera();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void OpenCamera() {
        // QRCODE_SCAN_TYPE and DATAMATRIX_SCAN_TYPE are set for the barcode format, indicating that Scan Kit will support only QR code and Data Matrix.
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},
                DEFAULT_VIEW);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
       if(
               permissions == null || grantResults == null || grantResults.length < 2 || grantResults[0] != PackageManager.PERMISSION_GRANTED){
           return;
       }
       if(requestCode == DEFAULT_VIEW){
           HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE).create();
           ScanUtil.startScan(this, REQUEST_CODE_SCAN_ONE,options);
       }
    }

    private void SendSMS() {
        VerifyCodeSettings settings = new VerifyCodeSettings.Builder()
                .action(VerifyCodeSettings.ACTION_REGISTER_LOGIN)
                .sendInterval(30)
                .locale(Locale.CHINA)
                .build();
        Task<VerifyCodeResult> task = AGConnectAuth.getInstance().requestVerifyCode("84", "352337342", settings);
        task.addOnSuccessListener(TaskExecutors.uiThread(), new OnSuccessListener<VerifyCodeResult>() {
            @Override
            public void onSuccess(VerifyCodeResult verifyCodeResult) {
                // The verification code request is successful.
                Functions.ShowToast(VerifyOTP.this, "code request is successful.");
            }
        }).addOnFailureListener(TaskExecutors.uiThread(), new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.i("VerifyOTP", "onFailure: " + e.getMessage());
                Functions.ShowToast(VerifyOTP.this, e.getMessage());
            }
        });
    }

    private void setViews() {
        btnScan = findViewById(R.id.btnScan);
        imgBack = findViewById(R.id.imgBack);
        txtToken = findViewById(R.id.token);
    }

    private void setListeners() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SCAN_ONE) {
            // Input an image for scanning and return the result.
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                // Display the parsing result.
                Log.i("VerifyOPT", "onActivityResult: "+obj.showResult);
                try {
                    JSONObject jsonObject = new JSONObject(obj.showResult);
                    txtToken.setText(jsonObject.getString("token"));
                    txtToken.setTextSize(32);
                    txtToken.setLetterSpacing(Float.parseFloat("0.3"));
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }
                Functions.ShowToast(VerifyOTP.this,obj.showResult);
            }
        }
    }


}