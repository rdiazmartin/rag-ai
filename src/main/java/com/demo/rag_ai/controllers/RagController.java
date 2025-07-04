package com.demo.rag_ai.controllers;

import com.demo.rag_ai.model.InfoRequest;
import com.demo.rag_ai.model.InfoResponse;
import com.demo.rag_ai.services.RagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {
    private RagService service;

    public RagController(RagService service) {
        this.service = service;
    }

    @GetMapping("/ia/info")
    public InfoResponse getInfo(InfoRequest msg) {
        return service.getInfo(msg);
    }
}
