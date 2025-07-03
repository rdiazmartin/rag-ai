package com.demo.rag_ai.services;

import com.demo.rag_ai.configuration.RagConfiguration;
import com.demo.rag_ai.model.InfoRequest;
import com.demo.rag_ai.model.InfoResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagServiceImpl implements RagService, CommandLineRunner {

    private final VectorStore vectorStore;
    private final ChatClient chatClient;
    private final ResourceLoader resourceLoader;
    private final RagConfiguration ragConfiguration;

    public RagServiceImpl(VectorStore vectorStore, ChatClient chatClient, ResourceLoader resourceLoader, RagConfiguration ragConfiguration) {
        this.vectorStore = vectorStore;
        this.chatClient = chatClient;
        this.resourceLoader = resourceLoader;
        this.ragConfiguration = ragConfiguration;
    }

    @Override
    public InfoResponse getInfo(InfoRequest msg) {
        ChatResponse response = chatClient.prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .user(msg.msg())
                .call()
                .chatResponse();
        assert response != null;
        return new InfoResponse(response.getResult().toString());
    }

    @Override
    public void run(String... args) throws Exception {
        Resource pdfResource = resourceLoader.getResource(String.format("classpath:%s", ragConfiguration.pathPdf()));
        // 2. Usamos TikaDocumentReader en lugar de PagePdfDocumentReader
        TikaDocumentReader tikaReader = new TikaDocumentReader(pdfResource);
        List<Document> documents = tikaReader.get();

        vectorStore.add(documents);

        System.out.println("PDF cargado con Tika. Total de 'Documentos' generados: " + documents.size());
    }
}
