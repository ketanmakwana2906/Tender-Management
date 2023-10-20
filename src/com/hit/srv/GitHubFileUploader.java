package com.hit.srv;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GitHubFileUploader {
    public static void main(String[] args) {
        String githubApiUrl = "https://api.github.com/repos/ketanmakwana2906/tender-management/WebContent/licence_docs/yourfile.txt";
        String accessToken = "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";

        // Content of the file you want to upload (base64 encoded)
        String fileContentBase64 = "SGVsbG8gV29ybGQ="; // Example: "Hello World"

        // JSON payload for the API request
        String jsonPayload = "{\"message\":\"Upload a file\",\"content\":\"" + fileContentBase64 + "\"}";

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(githubApiUrl);

            httpPut.addHeader("Authorization", "token " + accessToken);
            httpPut.addHeader("Content-Type", "application/json");

            StringEntity entity = new StringEntity(jsonPayload);
            httpPut.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPut);

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == 201) {
                System.out.println("File uploaded successfully.");
            } else {
                System.err.println("Error: " + statusCode);

                // Print the error response
                System.err.println("Error Response:");
                System.err.println(EntityUtils.toString(response.getEntity()));
            }

            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
