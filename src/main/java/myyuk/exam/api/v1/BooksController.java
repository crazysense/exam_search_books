package myyuk.exam.api.v1;

import myyuk.exam.model.Book;
import myyuk.exam.service.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    @Autowired
    private SearchBooksService searchBooksService;

    @GetMapping
    public List<Book> getBooksByKeyWord(@RequestParam String keyword) {
        return searchBooksService.search(keyword);
    }
}
