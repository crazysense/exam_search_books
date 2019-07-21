package myyuk.exam.service.impl;

import myyuk.exam.component.SearchBooksApiProvider;
import myyuk.exam.model.ApiRequest;
import myyuk.exam.model.Book;
import myyuk.exam.service.SearchBooksService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchBooksServiceImpl implements SearchBooksService {
    private static final Logger logger = LoggerFactory.getLogger(SearchBooksServiceImpl.class);

    @Autowired
    private SearchBooksApiProvider apiProvider;

    @Override
    public List<Book> search(String keyword) {
        List<ApiRequest> apis = apiProvider.getApis();
        if (apis == null || apis.isEmpty()) {
            logger.error("Not found SearchBooks API provider.");
            return new ArrayList<>(0);
        }

        List<Book> result = new ArrayList<>();
        for (ApiRequest api : apis) {
            result = search(api, keyword);
            if (result != null && !result.isEmpty()) {
                break;
            }
        }
        return result;
    }

    private List<Book> search(ApiRequest apiRequest, String keyword) {
        String apiName = apiRequest.getName();
        try {
            if (apiName != null && !apiName.trim().isEmpty()) {
                apiName = apiName.trim().toUpperCase();
                switch (apiName) {
                    case "KAKAO":
                        return searchForKakao(apiRequest, keyword);
                    case "NAVER":
                        return searchForNaver(apiRequest, keyword);
                    default:
                        logger.warn("Does not support API: " + apiRequest.getName());
                }
            }
        } catch (ParseException e) {
            logger.error("Error while parsing API response.", e);
        } catch (IOException e) {
            logger.error("Invalid API.");
        }
        return null;
    }

    private String request(ApiRequest apiRequest, String queryParam, String keyword) throws IOException {
        String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name());
        URL url = new URL(apiRequest.getUrl() + queryParam + encodedKeyword);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(RequestMethod.GET.name());
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        for (Map.Entry<String, String> header : apiRequest.getHeader().entrySet()) {
            conn.addRequestProperty(header.getKey(), header.getValue());
        }

        conn.setDoOutput(false);

        StringBuilder response = new StringBuilder();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (InputStreamReader is = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                 BufferedReader br = new BufferedReader(is)) {
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line).append(System.lineSeparator());
                }
            }
        }
        return response.toString();
    }

    private int parseNumber(Object value) {
        String str = String.valueOf(value);
        int result;
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            result = 0;
        }
        return result;
    }

    private String parseDatetimeKakao(Object value) {
        return ZonedDateTime.parse(String.valueOf(value)).toLocalDate().toString();
    }

    private String parseDatetimeNaver(Object value) {
        return LocalDate.parse(String.valueOf(value), DateTimeFormatter.ofPattern("yyyyMMdd")).toString();
    }

    private List<Book> searchForKakao(ApiRequest apiRequest, String keyword) throws IOException, ParseException {
        String response = request(apiRequest, "?target=title&query=", keyword);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        JSONArray array = (JSONArray) object.get("documents");

        List<Book> books = new ArrayList<>();
        for (Object item : array) {
            JSONObject jsonBook = (JSONObject) item;
            Book book = new Book();
            book.setTitle(String.valueOf(jsonBook.get("title")));
            book.setThumbnail(String.valueOf(jsonBook.get("thumbnail")));
            book.setContents(String.valueOf(jsonBook.get("contents")));
            book.setIsbn(String.valueOf(jsonBook.get("isbn")));
            book.setAuthor(String.join("|", (JSONArray) jsonBook.get("authors")));
            book.setPublisher(String.valueOf(jsonBook.get("publisher")));
            book.setDatetime(parseDatetimeKakao(jsonBook.get("datetime")));
            book.setPrice(parseNumber(jsonBook.get("price")));
            book.setSalePrice(parseNumber(jsonBook.get("sale_price")));

            books.add(book);
        }

        return books;
    }

    private List<Book> searchForNaver(ApiRequest apiRequest, String keyword) throws IOException, ParseException {
        String response = request(apiRequest, "?query=", keyword);
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);
        JSONArray array = (JSONArray) object.get("items");

        List<Book> books = new ArrayList<>();
        for (Object item : array) {
            JSONObject jsonBook = (JSONObject) item;
            Book book = new Book();
            book.setTitle(String.valueOf(jsonBook.get("title"))
                    .replace("<b>", "").replace("</b>", ""));
            book.setThumbnail(String.valueOf(jsonBook.get("image")));
            book.setContents(String.valueOf(jsonBook.get("description")));
            book.setIsbn(String.valueOf(jsonBook.get("isbn")));
            book.setAuthor(String.valueOf(jsonBook.get("author")));
            book.setPublisher(String.valueOf(jsonBook.get("publisher")));
            book.setDatetime(parseDatetimeNaver(jsonBook.get("pubdate")));
            book.setPrice(parseNumber(jsonBook.get("price")));
            book.setSalePrice(parseNumber(jsonBook.get("discount")));
            books.add(book);
        }

        return books;
    }
}
