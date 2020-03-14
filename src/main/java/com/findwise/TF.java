package com.findwise;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TF {

    private int occurringWord;
    private int allWords;
    private double tf;

    public TF(String term, List<String> documentContent) {
        this.occurringWord = countOccurringWord(term, documentContent);
        this.allWords = countAllWords(documentContent);
        this.tf = countTf();
    }

    private int countOccurringWord(String term, List<String> documentContent) {
        return documentContent.stream()
                .filter(e -> e.contains(term))
                .collect(Collectors.toList())
                .size();
    }

    private int countAllWords(List<String> documentContent) {
        return documentContent.size();
    }

    private double countTf() {
        return (this.occurringWord / (double) this.allWords);
    }
}
