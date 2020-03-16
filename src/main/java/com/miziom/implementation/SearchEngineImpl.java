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

    private static Map<String, String> docs;
    private static Map<String, List<TermInfo>> index;

    public SearchEngineImpl() {
        if (docs == null) {
            docs = new HashMap<>();
        }
        if(index == null){
            index = new HashMap<>();
        }
    }

    @Override
    public Map<String, String> getDocs() {
        return docs;
    }

    @Override
    public void indexDocument(String id, String content) {
        docs.put(id, content);
        List<String> docTokenize = new ArrayList<>();
        Matcher m = Pattern.compile(Globals.WORD_PATTERN).matcher(content);
        while (m.find()) {
            docTokenize.add(m.group());
        }
        int pos = 0;
        for (String token : docTokenize) {
            pos++;
            List<TermInfo> idx = index.get(token);
            if (idx == null) {
                idx = new ArrayList<>();
                index.put(token, idx);
            }
            idx.add(new TermInfo(id, pos));
        }
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
            Matcher m = Pattern.compile(Globals.WORD_PATTERN).matcher(entry.getValue());
            while (m.find()) {
                docTokenize.add(m.group());
            }
            docTokenize.replaceAll(e -> e.replaceAll(Globals.SUFFIX_PATTERN, ""));
            TF tf = new TF(term, docTokenize);
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
}
