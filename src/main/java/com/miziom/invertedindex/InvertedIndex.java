package com.miziom.invertedindex;

import java.util.*;

public class InvertedIndex {

    private static Map<String, List<TermInfo>> invertedIndex;

    public InvertedIndex() {
        if (invertedIndex == null) {
            invertedIndex = new HashMap<>();
        }
    }

    public int countOccurringWord(String docId, String term) {
        return (int) invertedIndex.get(term).stream()
                .filter(e -> e.getDocId().equals(docId))
                .count();
    }

    public int countDocsWithTerm(String term) {
        Set<String> set = new HashSet<>();
        for (TermInfo termInfo : invertedIndex.get(term)) {
            set.add(termInfo.getDocId());
        }
        return set.size();
    }

    public void addInvertedIndex(String id, List<String> docTokenize) {
        int pos = 0;
        for (String token : docTokenize) {
            pos++;
            List<TermInfo> idx = invertedIndex.computeIfAbsent(token, k -> new ArrayList<>());
            idx.add(new TermInfo(id, pos));
        }
    }
}
