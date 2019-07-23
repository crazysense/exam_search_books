package myyuk.exam.response;

public class History {
    private String keyword;
    private String datetime;

    public History() {
    }

    public History(String keyword, String datetime) {
        this.keyword = keyword;
        this.datetime = datetime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
