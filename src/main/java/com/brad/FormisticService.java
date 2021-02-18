package com.brad;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class FormisticService {
    private final static String FORMISTIC_URL = "http://api.forismatic.com/api/1.0/";

    private static String formRequestBody(String language) throws UnsupportedEncodingException {
        Map<String, String> formArguments = new HashMap();
        formArguments.put("method", "getQuote");
        formArguments.put("lang", language);
        formArguments.put("format", "xml");

        StringJoiner postString = new StringJoiner("&");
        for (Map.Entry<String, String> entry : formArguments.entrySet()) {
            postString.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return postString.toString();
    }

    public static String getQuote(String language) throws IOException {

        URL resourceUrl = new URL(FORMISTIC_URL);

        HttpURLConnection connection = (HttpURLConnection) resourceUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        connection.connect();

        String postString = formRequestBody(language);
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

        return response.toString();
    }
}
