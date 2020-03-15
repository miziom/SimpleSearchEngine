package com.findwise;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Getter
@Setter
public class FileLoader {

    private SearchEngine searchEngine;

    public FileLoader() {
        this.searchEngine = new SearchEngineImpl();
    }

    public void loadDoc(File file) throws IOException {
        if(file.getName().endsWith(Globals.TXT_PATTERN)){
            String id = file.getName();
            String content = Files.readString(file.toPath()).toLowerCase();
            searchEngine.indexDocument(id, content);
            System.out.println("File loaded.");
        }
        else {
            System.out.println("Not .txt file . . .");
        }
    }
}
