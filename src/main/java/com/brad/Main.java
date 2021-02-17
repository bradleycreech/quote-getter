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
 * Main class to house the functions to serialize and deserialize our Java objects.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            getQuote(LanguageMapper.map(args[0]));

        } else {
            printUsage();
        }

        return;
    }

    private static Forismatic getQuote(String language) throws IOException {

        URL resourceUrl = new URL("http://api.forismatic.com/api/1.0/");

        HttpURLConnection connection = (HttpURLConnection) resourceUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        Map<String, String> formArguments = new HashMap();
        formArguments.put("method", "getQuote");
        formArguments.put("lang", language);
        formArguments.put("format", "xml");

        StringJoiner postString = new StringJoiner("&");
        for (Map.Entry<String, String> entry : formArguments.entrySet()) {
            postString.add(
                    URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        connection.connect();

        try (OutputStream output = connection.getOutputStream()) {
            output.write(postString.toString().getBytes());
        }

        int responseCode = connection.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer response = new StringBuffer();
        String output;

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        XmlMapper xmlMapper = new XmlMapper();

        Forismatic forismatic = xmlMapper.readValue(response.toString(), Forismatic.class);

        //System.out.println(forismatic.quote.getQuoteText());
        System.out.println(forismatic.quote.getQuoteAuthor());

        return new Forismatic();
    }

    private static void printUsage() {
        System.out.println("Usage: getquote english|russian");
    }
}
