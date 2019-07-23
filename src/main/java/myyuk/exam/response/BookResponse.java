package myyuk.exam.response;

import myyuk.exam.model.Book;

import java.util.List;

public class BookResponse {
    private int totalCount;
    private List<Book> books;

    public BookResponse() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
