package com.findwise.ui;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Scanner;

@NoArgsConstructor
public class MenuEngine {

    private static Scanner scanner = new Scanner(System.in);
    private static final String ONE_TWO_TREE_PATTERN = "^(?:[1-3]|0)$";

    private static void printMainMenu() {
        System.out.println("=====================================");
        System.out.println("The Simple Search Engine - Main Menu");
        System.out.println("1. Add a document.");
        System.out.println("2. Analyze a document.");
        System.out.println("3. Exit");
        System.out.println("Type your decision (1 OR 2 OR 3):");
    }

    private static void printAddMenu() {
        System.out.println("================================================");
        System.out.println("The Simple Search Engine - Add a document Menu");
        System.out.println("1. Back to Main Menu");
        System.out.println("Enter 1 OR document path:");
    }

    private static void printAnalyzeMenu() {
        System.out.println("===================================================");
        System.out.println("The Simple Search Engine - Analyze a document Menu");
        System.out.println("1. Back to Main Menu");
        System.out.println("Enter 1 OR term:");
    }

    public static void mainMenu() throws IOException {
        printMainMenu();
        String decision = scanner.nextLine().replace(" ", "");
        if(decision.matches(ONE_TWO_TREE_PATTERN)){
            switchMainMenu(Integer.parseInt(decision));
        }
        else {
            System.out.println("Enter 1 OR 2 OR 3");
            System.out.print("Press any key to continue . . . ");
            scanner.nextLine();
        }
    }

    private static void switchMainMenu(int decision) throws IOException {
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

    private static boolean addMenu() throws IOException {
        printAddMenu();
        String decision = scanner.nextLine();
        return switchAddMenu(decision);
    }

    private static boolean switchAddMenu(String decision) {
        switch (decision) {
            case "1": {
                return false;
            }
            default: {
                //check path here
                return true;
            }
        }
    }

    private static boolean analyzeMenu() {
        printAnalyzeMenu();
        String decision = scanner.nextLine();
        return switchAnalyzeMenu(decision);
    }

    private static boolean switchAnalyzeMenu(String decision) {
        switch (decision) {
            case "1": {
                return false;
            }
            default: {
                //analyze here
                return true;
            }
        }
    }

}
