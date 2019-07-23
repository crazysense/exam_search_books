package myyuk.exam.service;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import myyuk.exam.response.History;

import java.util.List;

public interface HistoryService {
    List<History> getRecent10PerUser(String userId);

    Keyword save(Keyword keyword);

    Keyword getKeyword(KeywordPk pk);
}
