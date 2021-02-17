package com.brad;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("quote")
public class Forismatic {
    @JsonProperty("quote")
    public Quote quote;
}