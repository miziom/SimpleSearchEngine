package com.findwise;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        while (true){

            Scanner scanner = new Scanner(System.in);
            printMainMenu();
            String decisionMainManu = scanner.nextLine();


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

    private static void printMainMenu() throws IOException {
        System.out.println("==========================================");
        System.out.println("The Simple Search Engine");
        System.out.println("1. Add a document.");
        System.out.println("2. Analyze a document.");
        System.out.println("3. Exit");
        System.out.println("Type your decision: ");
    }

    private static void printAddMenu() throws IOException{
        System.out.println("==========================================");
        System.out.println("The Simple Search Engine");
        System.out.println("1. Back to Main Menu");
        System.out.println("Enter document path: ");
    }

    private static void printAnalyzeMenu(){
        System.out.println("==========================================");
        System.out.println("The Simple Search Engine");
        System.out.println("1. Back to Main Menu");
        System.out.println("1. Back to Main Menu");
    }

}


