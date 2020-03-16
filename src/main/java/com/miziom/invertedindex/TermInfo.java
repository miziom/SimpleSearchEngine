package com.miziom.invertedindex;


public class TermInfo {

    private String docId;
    private int positions;

    public TermInfo(String docId, int positions) {
        this.docId = docId;
        this.positions = positions;
    }
}
