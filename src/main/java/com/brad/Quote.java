package com.brad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"quoteText", "quoteAuthor", "senderName", "senderLink", "quoteLink"})
public class Quote {
    @JsonProperty("quoteText")
    String quoteText;
    @JsonProperty("quoteAuthor")
    String quoteAuthor;
    @JsonProperty("senderName")
    String senderName;
    @JsonProperty("senderLink")
    String senderLink;
    @JsonProperty("quoteLink")
    String quoteLink;

    public String getQuoteText(){
        return quoteText;
    }

    public String getQuoteAuthor(){
        return quoteAuthor;
    }
}
