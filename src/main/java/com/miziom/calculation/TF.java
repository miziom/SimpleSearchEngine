package com.miziom.calculation;

import lombok.Getter;

@Getter
public class TF {

    private int occurringWord;
    private int allWords;
    private double tf;

    public TF(int occurringWord, int allWords) {
        this.occurringWord = occurringWord;
        this.allWords = allWords;
        this.tf = countTf();
    }

    private double countTf() {
        return (this.occurringWord / (double) this.allWords);
    }
}
