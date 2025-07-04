package com.demo.rag_ai;

import com.demo.rag_ai.configuration.RagConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RagConfiguration.class)
public class RagAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RagAiApplication.class, args);
	}

}
