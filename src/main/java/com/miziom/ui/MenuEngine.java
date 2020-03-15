package com.miziom.ui;

import com.miziom.constant.Globals;
import com.findwise.IndexEntry;
import com.findwise.SearchEngine;
import com.miziom.implementation.SearchEngineImpl;
import com.miziom.filehandler.FileLoader;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor
public class MenuEngine {

    private static Scanner scanner = new Scanner(System.in);
    private FileLoader fileLoader = new FileLoader();
    private static SearchEngine searchEngine = new SearchEngineImpl();


    private void printMainMenu() {
        System.out.println("=====================================");
        System.out.println("The Simple Search Engine - Main Menu");
        System.out.println("1. Add a document.");
        System.out.println("2. Analyze a document.");
        System.out.println("3. Exit");
        System.out.println("Type your decision (1 OR 2 OR 3):");
    }

    private void printAddMenu() {
        System.out.println("================================================");
        System.out.println("The Simple Search Engine - Add a document Menu");
        printLoadedFile();
        System.out.println("1. Back to Main Menu");
        System.out.println("Enter 1 OR document path:");
    }

    private void printAnalyzeMenu() {
        System.out.println("===================================================");
        System.out.println("The Simple Search Engine - Analyze a document Menu");
        System.out.println("1. Back to Main Menu");
        System.out.println("Enter 1 OR term:");
    }

    private void printWaitForKey() {
        System.out.print("Press any key to continue . . . ");
        scanner.nextLine();
    }

    private void printLoadedFile() {
        System.out.println("Loaded documents:");
        for (Map.Entry<String, String> entry : searchEngine.getDocs().entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    private void printListEntry(List<IndexEntry> listIndexEntry, String term){
        System.out.println("Result for term: " + term);
        System.out.printf("%5s %30s", "POS", "DOCUMENT ID\n");
        listIndexEntry.forEach(indexEntry ->
                System.out.printf("%10s %30s", listIndexEntry.indexOf(indexEntry) + 1, indexEntry.getId() + "\n"));
    }

    public void mainMenu() throws IOException {
        printMainMenu();
        String decision = scanner.nextLine().trim();
        if (decision.matches(Globals.ONE_TWO_TREE_PATTERN)) {
            switchMainMenu(Integer.parseInt(decision));
        } else {
            System.out.println("Enter 1 OR 2 OR 3");
            printWaitForKey();
        }
    }

    private void switchMainMenu(int decision) throws IOException {
        switch (decision) {
            case 1: {
                while (addMenu()) ;
            }
            break;
            case 2: {
                while (analyzeMenu()) ;
            }
            break;
            case 3: {
                System.exit(0);
            }
            break;
        }
    }

    private boolean addMenu() throws IOException {
        printAddMenu();
        String decision = scanner.nextLine().trim();
        return switchAddMenu(decision);
    }

    private boolean switchAddMenu(String decision) throws IOException {
        if (decision.equals("1")) {
            return false;
        } else {
            File file = new File(decision);
            if (!file.exists()) {
                System.out.println("No file in: " + decision);
            } else {
                fileLoader.loadDoc(file);
            }
            printWaitForKey();
            return true;
        }
    }

    private boolean analyzeMenu() {
        printAnalyzeMenu();
        String decision = scanner.nextLine().trim();
        return switchAnalyzeMenu(decision);
    }

    private boolean switchAnalyzeMenu(String decision) {
        switch (decision) {
            case "1": {
                return false;
            }
            default: {
                List<IndexEntry> listIndexEntry = searchEngine.search(decision);
                printListEntry(listIndexEntry, decision);
                printWaitForKey();
                return true;
            }
        }
    }
}
