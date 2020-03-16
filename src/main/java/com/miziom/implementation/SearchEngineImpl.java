package com.miziom.implementation;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.miziom.calculation.IDF;
import com.miziom.calculation.TF;
import com.miziom.comparator.SortByScore;
import com.miziom.constant.Globals;
import com.miziom.invertedindex.TermInfo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchEngineImpl implements SearchEngine {

    private static Map<String, Integer> docs;
    private static Map<String, List<TermInfo>> invertedIndex;

    public SearchEngineImpl() {
        if (docs == null) {
            docs = new HashMap<>();
        }
        if (invertedIndex == null) {
            invertedIndex = new HashMap<>();
        }
    }

    @Override
    public Map<String, Integer> getDocs() {
        return docs;
    }

    @Override
    public void indexDocument(String id, String content) {
        List<String> docTokenize = new ArrayList<>();
        Matcher m = Pattern.compile(Globals.WORD_PATTERN).matcher(content);
        while (m.find()) {
            docTokenize.add(m.group());
        }
        docs.put(id, docTokenize.size());
        int pos = 0;
        for (String token : docTokenize) {
            pos++;
            List<TermInfo> idx = invertedIndex.computeIfAbsent(token, k -> new ArrayList<>());
            idx.add(new TermInfo(id, pos));
        }
    }

    @Override
    public List<IndexEntry> search(String term) {
        List<IndexEntry> listIndexEntry = new ArrayList<>();
        Map<String, TF> tfMap = new HashMap<>();
        prepareTfMap(term, tfMap);
        IDF idf = new IDF(countDocNumber(), countDocsWithTerm(term));
        countScore(listIndexEntry, tfMap, idf);
        Collections.sort(listIndexEntry, new SortByScore());
        return listIndexEntry;
    }

    private void prepareTfMap(String term, Map<String, TF> tfMap) {
        for (Map.Entry<String, Integer> entry : docs.entrySet()) {
            TF tf = new TF(
                    countOccurringWord(entry.getKey(), term),
                    countWordsInDoc(entry.getKey())
            );
            tfMap.put(entry.getKey(), tf);
        }
    }

    private void countScore(List<IndexEntry> listIndexEntry, Map<String, TF> tfMap, IDF idf) {
        for (Map.Entry<String, TF> entry : tfMap.entrySet()) {
            if (entry.getValue().getOccurringWord() > 0) {
                double score = idf.getIdf() * entry.getValue().getTf();
                listIndexEntry.add(new IndexEntryImpl(entry.getKey(), score));
            }
        }
    }

    private int countOccurringWord(String docId, String term) {
        return (int) invertedIndex.get(term).stream()
                .filter(e -> e.getDocId().equals(docId))
                .count();
    }

    private int countWordsInDoc(String docId) {
        return docs.get(docId);
    }

    private int countDocNumber() {
        return docs.size();
    }

    private int countDocsWithTerm(String term) {
        Set<String> set = new HashSet<>();
        for (TermInfo termInfo : invertedIndex.get(term)) {
            set.add(termInfo.getDocId());
        }
        return set.size();
    }
}
