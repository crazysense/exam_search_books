package myyuk.exam.component;

import myyuk.exam.model.ApiRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SearchBooksApiProviderTest {
    @Autowired
    private SearchBooksApiProvider searchBooks;

    @Test
    public void test() {
        List<ApiRequest> a = searchBooks.getApis();
        System.out.println(a);
    }

}