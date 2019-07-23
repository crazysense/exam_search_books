package myyuk.exam.service;

import myyuk.exam.response.KeywordRank;

import java.util.List;

public interface BookRankService {
    List<KeywordRank> getRankTop10();
}
