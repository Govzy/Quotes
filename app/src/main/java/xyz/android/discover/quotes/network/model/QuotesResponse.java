package xyz.android.discover.quotes.network.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuotesResponse {

    @JsonProperty("quote")
    private String quote;

    @JsonProperty("author")
    private String author;

    @JsonProperty("category")
    private String category;

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return
                "QuotesResponse{" +
                        "quote = '" + quote + '\'' +
                        ",author = '" + author + '\'' +
                        ",category = '" + category + '\'' +
                        "}";
    }
}