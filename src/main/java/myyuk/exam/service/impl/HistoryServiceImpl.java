package myyuk.exam.service.impl;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import myyuk.exam.entity.repo.KeywordRepo;
import myyuk.exam.response.History;
import myyuk.exam.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private KeywordRepo keywordRepo;

    @Override
    public List<History> getRecent10PerUser(String userId) {
        return convert(keywordRepo.findRecently10KeywordByUser(userId));
    }

    @Override
    public Keyword save(Keyword keyword) {
        return keywordRepo.save(keyword);
    }

    @Override
    public Keyword getKeyword(KeywordPk pk) {
        return keywordRepo.findByKeywordByUser(pk);
    }

    private List<History> convert(List<Object[]> result) {
        if (result != null && !result.isEmpty()) {
            return result.stream().map(obj -> {
                String dateTime = "";
                if (obj[1] != null && obj[1] instanceof Timestamp) {
                    LocalDateTime ldt = ((Timestamp) obj[1]).toLocalDateTime();
                    dateTime = ldt.toString().split("\\.")[0].replace('T', ' ');
                }
                return new History(String.valueOf(obj[0]), dateTime);
            }).collect(Collectors.toList());
        }
        return new ArrayList<>(0);
    }
}
