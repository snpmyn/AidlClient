package com.zsp.aidlclient.calculate;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.zsp.aidlclient.R;
import com.zsp.aidlserver.calculate.CalculateAIDL;

/**
 * @desc: 计算页
 * @author: zsp
 * @date: 2021/5/21 9:48 上午
 */
public class CalculateActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    /**
     * 控件
     */
    private EditText calculateActivityEtNumberOne;
    private EditText calculateActivityEtNumberTwo;
    private MaterialButton calculateActivityMbAdd;
    private MaterialButton calculateActivityMbMax;
    private MaterialButton calculateActivityMbMin;
    /**
     * CalculateAIDL
     */
    private CalculateAIDL calculateAidl;
    /**
     * ServiceConnection
     */
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            calculateAidl = CalculateAIDL.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected " + calculateAidl.hashCode());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            calculateAidl = null;
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        stepUi();
        setListener();
        startLogic();
    }

    private void stepUi() {
        calculateActivityEtNumberOne = findViewById(R.id.calculateActivityEtNumberOne);
        calculateActivityEtNumberTwo = findViewById(R.id.calculateActivityEtNumberTwo);
        calculateActivityMbAdd = findViewById(R.id.calculateActivityMbAdd);
        calculateActivityMbMax = findViewById(R.id.calculateActivityMbMax);
        calculateActivityMbMin = findViewById(R.id.calculateActivityMbMin);
    }

    private void setListener() {
        calculateActivityMbAdd.setOnClickListener(this);
        calculateActivityMbMax.setOnClickListener(this);
        calculateActivityMbMin.setOnClickListener(this);
    }

    private void startLogic() {
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.zsp.aidlserver");
        intent.setAction("com.zsp.aidlserver.calculate.action");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int x = Integer.parseInt(calculateActivityEtNumberOne.getText().toString());
        int y = Integer.parseInt(calculateActivityEtNumberTwo.getText().toString());
        if (null == calculateAidl) {
            Toast.makeText(this, "CalculateAIDL null", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            switch (v.getId()) {
                case R.id.calculateActivityMbAdd:
                    Toast.makeText(this, "ADD " + calculateAidl.add(x, y), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.calculateActivityMbMax:
                    Toast.makeText(this, "MAX " + calculateAidl.max(x, y), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.calculateActivityMbMin:
                    Toast.makeText(this, "MIN " + calculateAidl.min(x, y), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}