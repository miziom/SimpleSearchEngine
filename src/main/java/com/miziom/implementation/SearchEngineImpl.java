package com.miziom.implementation;

import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.miziom.calculation.IDF;
import com.miziom.calculation.TF;
import com.miziom.comparator.SortByScore;
import com.miziom.constant.Globals;
import com.miziom.filehandler.FileLoader;
import com.miziom.invertedindex.InvertedIndex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchEngineImpl implements SearchEngine {

    private static FileLoader fileLoader = new FileLoader();
    private InvertedIndex invertedIndex = new InvertedIndex();

    public SearchEngineImpl() {
    }

    @Override
    public void indexDocument(String id, String content) {
        List<String> docTokenize = new ArrayList<>();
        Matcher m = Pattern.compile(Globals.WORD_PATTERN).matcher(content);
        while (m.find()) {
            docTokenize.add(m.group());
        }
        fileLoader.addDoc(id, docTokenize);
        invertedIndex.addInvertedIndex(id, docTokenize);
    }

    @Override
    public List<IndexEntry> search(String term) {
        List<IndexEntry> listIndexEntry = new ArrayList<>();
        Map<String, TF> tfMap = new HashMap<>();
        fileLoader.prepareTfMap(term, tfMap);
        IDF idf = new IDF(
                fileLoader.countDocNumber(),
                invertedIndex.countDocsWithTerm(term));
        countScore(listIndexEntry, tfMap, idf);
        Collections.sort(listIndexEntry, new SortByScore());
        return listIndexEntry;
    }

    public void countScore(List<IndexEntry> listIndexEntry, Map<String, TF> tfMap, IDF idf) {
        for (Map.Entry<String, TF> entry : tfMap.entrySet()) {
            if (entry.getValue().getOccurringWord() > 0) {
                double score = idf.getIdf() * entry.getValue().getTf();
                listIndexEntry.add(new IndexEntryImpl(entry.getKey(), score));
            }
        }
    }
}
