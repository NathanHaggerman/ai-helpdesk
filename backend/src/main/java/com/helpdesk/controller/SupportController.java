package com.helpdesk.controller;

import com.helpdesk.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/support")
public class SupportController {

    @Autowired
    private OpenAiService openAiService;

    @PostMapping
    public ResponseEntity<String> askQuestion(@RequestBody String question) {
        String response = openAiService.askOpenAi(question);
        return ResponseEntity.ok(response);
    }
}
