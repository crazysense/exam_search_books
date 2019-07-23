package myyuk.exam.service.impl;

import myyuk.exam.entity.repo.KeywordRepo;
import myyuk.exam.response.KeywordRank;
import myyuk.exam.service.BookRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRankServiceImpl implements BookRankService {

    @Autowired
    private KeywordRepo keywordRepo;

    @Override
    public List<KeywordRank> getRankTop10() {
        return convert(keywordRepo.findTop10Keyword());
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
