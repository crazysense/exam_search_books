package myyuk.exam.rest.v1;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import myyuk.exam.request.KeywordUpdateRequest;
import myyuk.exam.response.KeywordRank;
import myyuk.exam.service.SearchRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
public class SearchRankController {

    @Autowired
    private SearchRankService searchRankService;

    private List<KeywordRank> top10Rank;

    @PostConstruct
    public void init() {
        this.syncTop10Keyword();
    }

    @Scheduled(fixedRate = 5000)
    public void syncTop10Keyword() {
        this.setTop10Keyword(searchRankService.getRankTop10());
    }

    @GetMapping
    public List<KeywordRank> searchRecent10KeywordsByUser(@RequestParam String userId) {
        return searchRankService.getRecent10PerUser(userId);
    }

    @PostMapping
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
        return searchRankService.save(keyword);
    }

    @GetMapping(value = "/rank")
    public List<KeywordRank> searchTop10Keywords() {
        return this.top10Rank;
    }

    private synchronized void setTop10Keyword(List<KeywordRank> top10Rank) {
        this.top10Rank = top10Rank;
    }

    private Keyword getKeyword(KeywordPk pk) {
        return searchRankService.getKeyword(pk);
    }
}
