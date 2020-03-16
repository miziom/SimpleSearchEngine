package com.miziom.calculation;

import lombok.Getter;

@Getter
public class IDF {

    private int docNumber;
    private int docWithTerm;
    private double idf;

    public IDF(int docNumber, int docWithTerm) {
        this.docNumber = docNumber;
        this.docWithTerm = docWithTerm;
        this.idf = countIdf();
    }

    private double countIdf() {
        return (Math.log((double) this.docNumber / (double) (this.docWithTerm) + 1) + 1);
    }
}
