package com.findwise;

import com.findwise.ui.MenuEngine;

import java.io.IOException;

public class Main {

    private static MenuEngine menuEngine = new MenuEngine();

    public static void main(String[] args) throws IOException {

        while (true) {
            menuEngine.mainMenu();
        }


//        String term = "fox";
//        FileLoader fileLoader = new FileLoader();
//        fileLoader.loadDoc("doc1.txt");
//        fileLoader.loadDoc("doc2.txt");
//        fileLoader.loadDoc("doc3.txt");
//
//        List<IndexEntry> indexEntries = fileLoader.getSearchEngine().search(term);
//        System.out.println(indexEntries);
    }
}


