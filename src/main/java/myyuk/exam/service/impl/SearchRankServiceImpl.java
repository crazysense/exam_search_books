package myyuk.exam.service.impl;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import myyuk.exam.entity.repo.KeywordRepo;
import myyuk.exam.response.KeywordRank;
import myyuk.exam.service.SearchRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchRankServiceImpl implements SearchRankService {

    @Autowired
    private KeywordRepo keywordRepo;

    @Override
    public List<KeywordRank> getRankTop10() {
        return convert(keywordRepo.findTop10Keyword());
    }

    @Override
    public List<KeywordRank> getRecent10PerUser(String userId) {
        return convert(keywordRepo.findRecently10KeywordByUser(userId));
    }

    @Override
    public Keyword getKeyword(KeywordPk pk) {
        return keywordRepo.findByKeywordByUser(pk);
    }

    @Override
    public Keyword save(Keyword keyword) {
        return keywordRepo.save(keyword);
    }

    private List<KeywordRank> convert(List<Object[]> result) {
        if (result != null && !result.isEmpty()) {
            return result.stream()
                    .map(obj -> new KeywordRank(String.valueOf(obj[0]), Integer.valueOf(String.valueOf(obj[1]))))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }
}
