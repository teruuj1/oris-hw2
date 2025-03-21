package ru.itis304.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class ApiController {

    public ApiController() {
    }

    private static final String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/posts";


    @PostMapping("/post")
    public ResponseEntity<String> post(@RequestBody String body) {
        try {
            URL url = new URL(EXTERNAL_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (var outputStream = connection.getOutputStream()) {
                outputStream.write(body.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 201) {
                return ResponseEntity.ok("Post created successfully");
            } else {
                return ResponseEntity.status(responseCode).body("Failed to create post");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> get(@PathVariable int id) {
        try {
            URL url = new URL(EXTERNAL_API_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return ResponseEntity.ok(response.toString());
                }
            } else {
                return ResponseEntity.status(responseCode).body("Failed to fetch post");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<String> put(@PathVariable int id, @RequestBody String body) {
        try {
            URL url = new URL(EXTERNAL_API_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (var outputStream = connection.getOutputStream()) {
                outputStream.write(body.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return ResponseEntity.ok("Post updated successfully");
            } else {
                return ResponseEntity.status(responseCode).body("Failed to update post");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            URL url = new URL(EXTERNAL_API_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return ResponseEntity.ok("Post deleted successfully");
            } else {
                return ResponseEntity.status(responseCode).body("Failed to delete post");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}