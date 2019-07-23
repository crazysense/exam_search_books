package myyuk.exam.rest.v1;

import myyuk.exam.response.KeywordRank;
import myyuk.exam.service.BookRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookRankController {

    @Autowired
    private BookRankService bookRankService;

    private List<KeywordRank> top10Rank;

    @PostConstruct
    public void init() {
        this.syncTop10Keyword();
    }

    @Scheduled(fixedRate = 5000)
    public void syncTop10Keyword() {
        this.setTop10Keyword(bookRankService.getRankTop10());
    }

    @GetMapping(value = "/rank")
    public List<KeywordRank> searchTop10Keywords() {
        return this.top10Rank;
    }

    private synchronized void setTop10Keyword(List<KeywordRank> top10Rank) {
        this.top10Rank = top10Rank;
    }

}
