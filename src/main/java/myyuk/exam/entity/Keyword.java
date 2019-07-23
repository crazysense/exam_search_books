package myyuk.exam.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "KEYWORD")
public class Keyword {
    @EmbeddedId
    private KeywordPk keywordByUser;

    @Column(name = "SEARCH_COUNT")
    private int searchCount;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    public Keyword() {

    }

    public Keyword(KeywordPk keywordByUser, int searchCount) {
        this(keywordByUser, searchCount, null);
    }

    public Keyword(KeywordPk keywordByUser, int searchCount, LocalDateTime updateTime) {
        this.keywordByUser = keywordByUser;
        this.searchCount = searchCount;
        this.updateTime = updateTime;
    }

    public KeywordPk getKeywordByUser() {
        return keywordByUser;
    }

    public void setKeywordByUser(KeywordPk keywordByUser) {
        this.keywordByUser = keywordByUser;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
