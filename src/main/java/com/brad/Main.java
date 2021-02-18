package com.brad;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Main class to house the functionality
 */
public class Main {

    public static void main(String[] args) throws IOException {

        try {
            if (args.length > 0) {
                displayResult(FormisticService.getQuote(LanguageMapper.map(args[0])));
            } else {
                printUsage();
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("Invalid parameter '" + e.getMessage() + "'");
        }

        return;
    }

    private static void displayResult(String response) throws IOException{

        XmlMapper xmlMapper = new XmlMapper();
        Forismatic forismatic = xmlMapper.readValue(response.toString(), Forismatic.class);

        System.out.println(forismatic.quote.getQuoteText());
        System.out.println(forismatic.quote.getQuoteAuthor());
    }



    private static void printUsage() {
        System.out.println("Usage: getquote english|russian");
    }
}
