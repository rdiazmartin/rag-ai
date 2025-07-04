package com.demo.rag_ai.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rag")
public record RagConfiguration(String pathPdf) {
}
