package com.findwise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileLoader {

    public boolean loadFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        else {
            SearchEngine searchEngine = new DocRepo();
            String id = file.getName();
            String content = Files.readString(file.toPath());
            searchEngine.indexDocument(id, content);
            return true;
        }
    }

}
