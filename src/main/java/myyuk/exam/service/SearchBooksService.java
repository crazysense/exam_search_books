package myyuk.exam.service;

import myyuk.exam.model.Book;

import java.util.List;

public interface SearchBooksService {
    List<Book> search(String keyword);
}
