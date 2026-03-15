package com.zsp.aidlclient.book;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.zsp.aidlclient.R;
import com.zsp.aidlserver.book.Book;
import com.zsp.aidlserver.book.BookAIDL;

import java.util.List;
import java.util.Objects;

/**
 * @desc: 书籍页
 * @author: zsp
 * @date: 2021/5/21 9:48 上午
 */
public class BookActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    /**
     * 控件
     */
    private MaterialButton bookActivityMbGetBookList;
    private MaterialButton bookActivityMbAddBook;
    /**
     * BookAIDL
     */
    private BookAIDL bookAidl;
    /**
     * ServiceConnection
     */
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookAidl = BookAIDL.Stub.asInterface(service);
            Log.d(TAG, "onServiceConnected " + bookAidl.hashCode());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bookAidl = null;
            Log.d(TAG, "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        stepUi();
        setListener();
        startLogic();
    }

    private void stepUi() {
        bookActivityMbGetBookList = findViewById(R.id.bookActivityMbGetBookList);
        bookActivityMbAddBook = findViewById(R.id.bookActivityMbAddBook);
    }

    private void setListener() {
        bookActivityMbGetBookList.setOnClickListener(this);
        bookActivityMbAddBook.setOnClickListener(this);
    }

    private void startLogic() {
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        // Android 5.0 以后官方推荐使用显式 Intent
        // 不需要 intent-filter
        // 不需要 action
        // 不需要 category
        // 更安全
        // 启动更快
        /*intent.setPackage("com.zsp.aidlserver");*/
        /*intent.setAction("com.zsp.aidlserver.book.action");*/
        intent.setComponent(new ComponentName("com.zsp.aidlserver", "com.zsp.aidlserver.book.BookService"));
        boolean result = bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        Toast.makeText(this, "绑定服务 " + result, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (null == bookAidl) {
            Toast.makeText(this, "BookAIDL 空", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int id = v.getId();
            if (id == R.id.bookActivityMbGetBookList) {
                Toast.makeText(this, getBookName(bookAidl.getBookList()), Toast.LENGTH_SHORT).show();
            } else if (id == R.id.bookActivityMbAddBook) {
                Book book = new Book("客户端添加新书 TAG InOut");
                bookAidl.addBookWithInOut(book);
                Toast.makeText(this, book.getName(), Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    @NonNull
    private String getBookName(@NonNull List<Book> bookList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : bookList) {
            stringBuilder.append(book.getName()).append("\n");
        }
        return stringBuilder.toString();
    }
}