package myyuk.exam.service;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import myyuk.exam.response.KeywordRank;

import java.util.List;

public interface SearchRankService {
    List<KeywordRank> getRankTop10();

    List<KeywordRank> getRecent10PerUser(String userId);

    Keyword getKeyword(KeywordPk pk);

    Keyword save(Keyword keyword);
}
