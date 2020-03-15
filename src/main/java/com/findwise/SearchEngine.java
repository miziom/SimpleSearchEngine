package com.findwise;

import java.util.List;
import java.util.Map;

public interface SearchEngine {
    /**
     * Add a document to the index
     * @param id name of the indexed document
     * @param content content of the document
     */
    void indexDocument(String id, String content);
    /**
     * Search the index for the given term
     * @param term to be found
     * @return Sorted list of search results containing the given term
     */
    List<IndexEntry> search(String term);

    /**
     *
     * @return Map with loaded documents
     */
    Map<String, String> getDocs();
}
