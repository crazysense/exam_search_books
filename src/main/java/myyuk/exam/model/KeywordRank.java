package myyuk.exam.model;

public class KeywordRank {
    private String keyword;
    private int searchCount;

    public KeywordRank() {
    }

    public KeywordRank(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }
}
