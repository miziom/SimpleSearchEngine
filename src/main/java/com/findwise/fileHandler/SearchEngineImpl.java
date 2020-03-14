package com.findwise.fileHandler;

import com.findwise.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngineImpl implements SearchEngine {

    private static Map<String, String> docs;
    private static final String WORD_PATTERN = "\\b[^\\s]+\\b";
    private static final String SUFFIX_PATTERN = "\\W+[^\\W]*$";

    public SearchEngineImpl() {
        if (docs == null) {
            this.docs = new HashMap<>();
        }
    }

    @Override
    public void indexDocument(String id, String content) {
        this.docs.put(id, content);
    }

    @Override
    public List<IndexEntry> search(String term) {
        List<IndexEntry> listIndexEntry = new ArrayList<>();
        Map<String, TF> tfMap = new HashMap<>();
        prepareTfMap(term, tfMap);
        IDF idf = new IDF(docs, tfMap);
        countScore(listIndexEntry, tfMap, idf);
        Collections.sort(listIndexEntry, new SortByScore());
        return listIndexEntry;
    }

    private void prepareTfMap(String term, Map<String, TF> tfMap) {
        for (Map.Entry<String, String> entry : docs.entrySet()) {
            List<String> docTokenize = new ArrayList<>();
            Matcher m = Pattern.compile(WORD_PATTERN).matcher(entry.getValue());
            while (m.find()) {
                docTokenize.add(m.group());
            }
            docTokenize.replaceAll(e -> e.replaceAll(SUFFIX_PATTERN, ""));
            TF tf = new TF(term, docTokenize);
            tfMap.put(entry.getKey(), tf);
            System.out.println(docTokenize);
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
}
