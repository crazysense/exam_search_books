package myyuk.exam.controller;

import myyuk.exam.model.History;
import myyuk.exam.model.KeywordRank;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private static final int maxSize = 10;

    private Map<String, Integer> keywordMap;
    private List<KeywordRank> keywordRankTop10;

    @PostConstruct
    private void initialize() {
        this.loadTop10();
    }

    private void loadTop10() {
        this.keywordMap = new HashMap<>();
        this.keywordRankTop10 = new LinkedList<>();

        List<KeywordRank> keywordRanks = new ArrayList<>(); // TODO:
        for (KeywordRank keywordRank : keywordRanks) {
            this.keywordMap.put(keywordRank.getKeyword(), keywordRank.getSearchCount());
            this.keywordRankTop10.add(keywordRank);
        }
    }

    private KeywordRank getKeywordRankFromDatabase(String keyword) {
        return null;
    }

    private void updateKeywordRankToDatabase(KeywordRank keywordRank) {
        // TODO
    }

    private KeywordRank incrementSearchCount(String keyword) {
        if (this.keywordMap.containsKey(keyword)) {
            this.keywordMap.put(keyword, this.keywordMap.get(keyword) + 1);
        } else {
            KeywordRank keywordRank = getKeywordRankFromDatabase(keyword);
            if (keywordRank == null) {
                keywordRank = new KeywordRank(keyword, 0);
            }
            keywordRank.setSearchCount(keywordRank.getSearchCount() + 1);

            if (maxSize == this.keywordRankTop10.size()) {
                KeywordRank lowerRank = this.keywordRankTop10.get(maxSize - 1);
                if (keywordRank.getSearchCount() > lowerRank.getSearchCount()) {
                    this.keywordMap.remove(lowerRank.getKeyword());
                    this.keywordMap.put(keyword, keywordRank.getSearchCount());
                }
            } else {
                this.keywordMap.put(keyword, keywordRank.getSearchCount());
            }
        }

        KeywordRank removeKeywordRank = null;
        for (KeywordRank item : this.keywordRankTop10) {
            removeKeywordRank = item;
            if (item.getKeyword().equals(keyword)) {
                break;
            }
        }
        if (removeKeywordRank != null) {
            this.keywordRankTop10.remove(removeKeywordRank);
        }

        KeywordRank keywordRank = new KeywordRank(keyword, this.keywordMap.get(keyword));
        this.keywordRankTop10.add(keywordRank);
        this.updateKeywordRankToDatabase(keywordRank);

        return keywordRank;
    }

    public List<History> getUserHistory(String userId) {
        return null;
    }

    public List<KeywordRank> getTop10() {
        return new ArrayList<>(this.keywordRankTop10);
    }
}
