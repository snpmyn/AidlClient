package com.zsp.aidlserver.book;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

/**
 * Created on 2021/5/21
 *
 * @author zsp
 * @desc 书籍
 */
public class Book implements Parcelable {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Book(@NonNull Parcel in) {
        name = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<>() {
        @NonNull
        @Contract("_ -> new")
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @NonNull
        @Contract(value = "_ -> new", pure = true)
        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
    }

    public void readFromParcel(@NonNull Parcel dest) {
        name = dest.readString();
    }
}