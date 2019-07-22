package myyuk.exam.model;

import javax.persistence.*;

@Entity
@Table(name = "KEYWORD")
public class Keyword {
    @EmbeddedId
    private KeywordPk keywordByUser;

    @Column(name = "SEARCH_COUNT")
    private int searchCount;

    public Keyword() {
    }

    public Keyword(KeywordPk keywordByUser, int searchCount) {
        this.keywordByUser = keywordByUser;
        this.searchCount = searchCount;
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
}
