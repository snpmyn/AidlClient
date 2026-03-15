package com.zsp.aidlclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
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

    @Override
    public void onClick(@NonNull View v) {
        int id = v.getId();
        if (id == R.id.mainActivityMbCalculate) {
            // 计算页
            startActivity(new Intent(this, CalculateActivity.class));
        } else if (id == R.id.mainActivityMbBook) {
            // 书籍页
            startActivity(new Intent(this, BookActivity.class));
        }
    }
}