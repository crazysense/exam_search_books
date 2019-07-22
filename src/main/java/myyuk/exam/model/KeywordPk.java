package myyuk.exam.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class KeywordPk implements Serializable {
    @Column(name = "KEYWORD")
    private String keyword;
    @Column(name = "USER_ID")
    private String userId;

    public KeywordPk() {
    }

    public KeywordPk(String keyword, String userId) {
        this.keyword = keyword;
        this.userId = userId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
