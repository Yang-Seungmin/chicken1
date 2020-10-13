package be.ysmstudio.myapplication;

public enum SearchEngine {
    GOOGLE("Google", "https://www.google.com/search?q="),
    NAVER("Naver", "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="),
    DAUM("Daum", "https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&sq=&o=&q=");

    private String name;
    private String urlPrefix;

    SearchEngine(String name, String urlPrefix) {
        this.name = name;
        this.urlPrefix = urlPrefix;
    }

    public String getName() {
        return name;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }
}
