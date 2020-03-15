package com.miziom.implementation;

import com.findwise.IndexEntry;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndexEntryImpl implements IndexEntry {

    private String id;
    private double score;

    public IndexEntryImpl(String id, double score) {
        this.id = id;
        this.score = score;
    }
}
