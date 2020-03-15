package com.miziom.comparator;

import com.findwise.IndexEntry;

import java.util.Comparator;

public class SortByScore implements Comparator<IndexEntry> {

    @Override
    public int compare(IndexEntry o1, IndexEntry o2) {
        if (o1.getScore() > o2.getScore())
            return -1;
        if (o1.getScore() == o2.getScore())
            return 0;
        else return 1;
    }
}
