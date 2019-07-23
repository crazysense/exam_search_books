package myyuk.exam.entity.repo;

import myyuk.exam.entity.Keyword;
import myyuk.exam.entity.KeywordPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeywordRepo extends JpaRepository<Keyword, KeywordPk> {
    @Query(nativeQuery = true,
            value = "select k.keyword as keyword, " +
                    "sum(k.search_count) as search_count " +
                    "from keyword k " +
                    "group by keyword " +
                    "order by search_count desc limit 10")
    List<Object[]> findTop10Keyword();

    @Query(nativeQuery = true,
            value = "select k.keyword as keyword, " +
                    "k.search_count as search_count " +
                    "from keyword k " +
                    "where k.user_id = ?1 " +
                    "order by update_time desc limit 10")
    List<Object[]> findRecently10KeywordByUser(String userId);

    Keyword findByKeywordByUser(KeywordPk pk);

    Keyword save(Keyword keyword);
}
