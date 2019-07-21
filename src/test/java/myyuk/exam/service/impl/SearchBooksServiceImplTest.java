package myyuk.exam.service.impl;

import myyuk.exam.service.SearchBooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SearchBooksServiceImplTest {
    @Autowired
    private SearchBooksService searchBooksService;

    @Test
    public void test2() {
        String date = "2014-11-17T00:00:00.000+09:00";
        String format = "yyyy-MM-dd";

        OffsetDateTime odt = OffsetDateTime.parse(date);
        try {
            LocalDate a = LocalDate.parse("20141117", DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate b = ZonedDateTime.parse("2014-11-17T00:00:00.000+09:00").toLocalDate();
            Date dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(date);
            System.out.println(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        searchBooksService.search("미움받을 용기");
    }
}