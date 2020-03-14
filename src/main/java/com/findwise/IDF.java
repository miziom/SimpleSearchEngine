package com.findwise;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class IDF {

    private int docNumber;
    private int docWithTerm;
    private double idf;

    public IDF(Map<String, String> docs, Map<String, TF> tfMap) {
        this.docNumber = countDocNumber(docs);
        this.docWithTerm = countDocWithTerm(tfMap);
        this.idf = countIdf();
    }

    private int countDocNumber(Map<String, String> docs) {
        return docs.size();
    }

    private int countDocWithTerm(Map<String, TF> tfMap) {
        return tfMap.entrySet().stream()
                .filter(e -> e.getValue().getOccurringWord() > 0)
                .collect(Collectors.toList())
                .size();
    }

    private double countIdf() {
        return Math.log((double) this.docNumber / (double) this.docWithTerm);
    }
}
