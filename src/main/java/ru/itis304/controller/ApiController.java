package ru.itis304.controller;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class ApiController {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts/";

    @GetMapping("/get")
    public String getPosts() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        return readResponse(connection);
    }

    @GetMapping("/get/{id}")
    public String getPost(@PathVariable("id") String id) throws IOException {
        URL url = new URL(API_URL + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        return readResponse(connection);
    }

    @PostMapping("/post")
    public String postPost(@RequestBody String jsonInput) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }
        return readResponse(connection);
    }

    @PutMapping("/put/{id}")
    public String putPost(@PathVariable("id") String id, @RequestBody String jsonInput) throws IOException {
        URL url = new URL(API_URL + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        return readResponse(connection);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") String id) throws IOException {
        URL url = new URL(API_URL + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        return readResponse(connection);
    }


    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } finally {
            connection.disconnect();
        }
        return content.toString();
    }
}