package com.demo.rag_ai.services;

import com.demo.rag_ai.model.InfoRequest;
import com.demo.rag_ai.model.InfoResponse;

public interface RagService {
    public InfoResponse getInfo(InfoRequest msg);
}
