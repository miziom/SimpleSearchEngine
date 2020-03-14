package com.findwise.fileHandler;

import com.findwise.SearchEngine;
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

    public boolean loadDoc(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        } else {
            String id = file.getName();
            String content = Files.readString(file.toPath()).toLowerCase();
            searchEngine.indexDocument(id, content);
            return true;
        }
    }
}
