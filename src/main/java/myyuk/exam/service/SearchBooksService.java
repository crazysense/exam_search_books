package myyuk.exam.service;

import myyuk.exam.response.BookResponse;

public interface SearchBooksService {
    BookResponse search(String keyword, int page);
}
