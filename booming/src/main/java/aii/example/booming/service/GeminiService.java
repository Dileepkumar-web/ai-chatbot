package aii.example.booming.service;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String API_URL_BASE =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=";

    public List<String> askGemini(String userQuestion) {
        try {
            // ✅ Build JSON request
            JsonObject message = new JsonObject();
            JsonArray contents = new JsonArray();

            JsonObject content = new JsonObject();
            content.addProperty("role", "user");
            JsonArray parts = new JsonArray();
            JsonObject part = new JsonObject();
            part.addProperty("text", userQuestion);
            parts.add(part);
            content.add("parts", parts);

            contents.add(content);
            message.add("contents", contents);

            // ✅ Send request
            HttpURLConnection conn = (HttpURLConnection) new URL(API_URL_BASE + apiKey).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(message.toString().getBytes(StandardCharsets.UTF_8));
            }

            // ✅ Read response or error
            InputStream is = (conn.getResponseCode() < 400) ? conn.getInputStream() : conn.getErrorStream();
            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();

                if (response.has("error")) {
                    return Collections.singletonList("Gemini API Error: " + response.get("error").toString());
                }

                String fullText = response
                        .getAsJsonArray("candidates")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("content")
                        .getAsJsonArray("parts")
                        .get(0).getAsJsonObject()
                        .get("text").getAsString();

                List<String> cleaned = new ArrayList<>();
                for (String line : fullText.split("\\n\\s*")) {
                    if (!line.trim().isEmpty()) cleaned.add(line.trim());
                }
                return cleaned;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.singletonList("Failed to contact Gemini: " + e.getMessage());
        }
    }
}
