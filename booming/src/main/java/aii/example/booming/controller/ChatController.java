package aii.example.booming.controller;

import aii.example.booming.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/")
    public String index() {
        return "chat"; // Matches your chat.html
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestParam("question") String question, Model model) {
        List<String> responseLines = geminiService.askGemini(question);
        model.addAttribute("question", question);
        model.addAttribute("responseLines", responseLines);
        return "chat"; // Reloads the same page with response
    }
}
