package myyuk.exam.rest.v1;

import myyuk.exam.response.BookResponse;
import myyuk.exam.service.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    @Autowired
    private SearchBooksService searchBooksService;

    @GetMapping
    public BookResponse getBooksByKeyWord(@RequestParam String keyword, @RequestParam Integer page) {
        return searchBooksService.search(keyword, page);
    }
}
