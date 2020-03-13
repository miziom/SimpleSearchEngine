package com.findwise;

import com.findwise.fileHandler.DocRepo;
import com.findwise.fileHandler.FileLoader;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Do sth");
        FileLoader fileLoader = new FileLoader();
        fileLoader.loadFile("doc1.txt");
        fileLoader.loadFile("doc2.txt");
        fileLoader.loadFile("doc3.txt");
        DocRepo docRepo = new DocRepo();
        Map<String, String> map = docRepo.getDocs();
    }
}
