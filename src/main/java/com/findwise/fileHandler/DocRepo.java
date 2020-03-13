package com.findwise.fileHandler;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;

import java.util.List;
import java.util.Map;

public class DocRepo implements SearchEngine {
    private static Map<String, String> docs;

    @Override
    public void indexDocument(String id, String content) {
        this.docs.put(id, content);
    }

    @Override
    public List<IndexEntry> search(String term) {
        return null;
    }
}
