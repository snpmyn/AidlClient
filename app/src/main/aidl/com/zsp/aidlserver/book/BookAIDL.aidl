// BookAIDL.aidl
package com.zsp.aidlserver.book;
import com.zsp.aidlserver.book.Book;

// Declare any non-default types here with import statements

interface BookAIDL {
    List<Book> getBookList();
    void addBookWithInOut(inout Book book);
}