package com.bigdata.app;

import java.util.Base64;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {
    public static void main(String... args) throws IOException {

    String username = "sayali.sonawane@colorado.edu";
    String password = "HashtagChronicles";
    String gnipURL = "https://gnip-api.twitter.com/search/fullarchive/accounts/greg-students/prod.json";
    String query = "gnip";

    String charset = "UTF-8";

	String queryURL = String.format("%s?query=%s&publisher=twitter", gnipURL, java.net.URLEncoder.encode(query, charset));

        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = getConnection(queryURL, username, password);

            inputStream = connection.getInputStream();
            int responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 299) {

                BufferedReader reader = new BufferedReader(new InputStreamReader((inputStream), charset));
                String line = reader.readLine();

                while(line != null){
                    System.out.println(line);
                    line = reader.readLine();
                }
            } else {
                handleNonSuccessResponse(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                handleNonSuccessResponse(connection);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static void handleNonSuccessResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        String responseMessage = connection.getResponseMessage();
        System.out.println("Non-success response: " + responseCode + " -- " + responseMessage);
    }

    private static HttpURLConnection getConnection(String urlString, String username, String password) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(1000 * 60 * 60);
        connection.setConnectTimeout(1000 * 10);

        connection.setRequestProperty("Authorization", createAuthHeader(username, password));

   return connection;
    }

    private static String createAuthHeader(String username, String password) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        String authToken = username + ":" + password;
        return "Basic " + encoder.encodeToString(authToken.getBytes());
    }
}
