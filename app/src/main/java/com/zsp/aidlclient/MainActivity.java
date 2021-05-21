package com.zsp.aidlclient;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.zsp.aidlclient.book.BookActivity;
import com.zsp.aidlclient.calculate.CalculateActivity;

/**
 * @desc: 主页
 * @author: zsp
 * @date: 2021/5/20 5:10 下午
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialButton mainActivityMbCalculate;
    private MaterialButton mainActivityMbBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepUi();
        setListener();
    }

    private void stepUi() {
        mainActivityMbCalculate = findViewById(R.id.mainActivityMbCalculate);
        mainActivityMbBook = findViewById(R.id.mainActivityMbBook);
    }

    private void setListener() {
        mainActivityMbCalculate.setOnClickListener(this);
        mainActivityMbBook.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainActivityMbCalculate:
                startActivity(new Intent(this, CalculateActivity.class));
                break;
            case R.id.mainActivityMbBook:
                startActivity(new Intent(this, BookActivity.class));
                break;
            default:
                break;
        }
    }
}