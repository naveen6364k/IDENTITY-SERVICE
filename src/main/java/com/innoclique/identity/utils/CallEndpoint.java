package com.innoclique.identity.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallEndpoint {
    public static void main(String[] args) {
        try {
            // Define the URL of the endpoint
            URL url = new URL("http://54.225.183.254:8082/patientDetails/getAllPatients");

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method
            connection.setRequestMethod("GET");

            // Set additional headers if needed
            // connection.setRequestProperty("Content-Type", "application/json");
            // connection.setRequestProperty("Authorization", "Bearer <your_token>");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response body
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response body
            System.out.println("Response Body: " + response.toString());

            // Disconnect the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
