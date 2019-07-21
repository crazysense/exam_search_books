package myyuk.exam.controller;

import myyuk.exam.model.Book;
import myyuk.exam.service.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchBooksController {
    @Autowired
    private SearchBooksService searchBooksService;

    @GetMapping("/search_books")
    public List<Book> getBooks(@RequestParam String keyword) {
        return searchBooksService.search(keyword);
    }
}
