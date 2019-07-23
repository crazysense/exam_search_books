package myyuk.exam.rest.v1;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import myyuk.exam.request.KeywordUpdateRequest;
import myyuk.exam.response.History;
import myyuk.exam.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/history")
    public List<History> searchRecent10KeywordsByUser(@RequestParam String userId) {
        return historyService.getRecent10PerUser(userId);
    }

    @PostMapping(value = "/history")
    public Keyword updateHistory(@RequestBody KeywordUpdateRequest request) {
        KeywordPk pk = new KeywordPk(request.getKeyword(), request.getUserId());
        Keyword keyword = getKeyword(pk);
        if (keyword == null) {
            keyword = new Keyword(pk, 1);
        } else {
            keyword.setSearchCount(keyword.getSearchCount() + 1);
        }
        keyword.setUpdateTime(LocalDateTime.parse(request.getDatetime(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return historyService.save(keyword);
    }

    private Keyword getKeyword(KeywordPk pk) {
        return historyService.getKeyword(pk);
    }
}
