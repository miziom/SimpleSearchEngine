package com.miziom.filehandler;

import com.findwise.SearchEngine;
import com.miziom.calculation.TF;
import com.miziom.constant.Globals;
import com.miziom.implementation.SearchEngineImpl;
import com.miziom.invertedindex.InvertedIndex;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class FileLoader {

    private static Map<String, Integer> docs;
    private InvertedIndex invertedIndex = new InvertedIndex();
    private SearchEngine searchEngine;


    public FileLoader() {
        this.searchEngine = new SearchEngineImpl();
        if (docs == null) {
            docs = new HashMap<>();
        }
    }

    public Map<String, Integer> getDocs() {
        return docs;
    }

    public boolean isDoc(){
        return !docs.isEmpty();
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

    public void addDoc(String id, List<String> docTokenize){
        docs.put(id, docTokenize.size());
    }

    public void prepareTfMap(String term, Map<String, TF> tfMap) {
        for (Map.Entry<String, Integer> entry : docs.entrySet()) {
            TF tf = new TF(
                    invertedIndex.countOccurringWord(entry.getKey(), term),
                    countWordsInDoc(entry.getKey())
            );
            tfMap.put(entry.getKey(), tf);
        }
    }

    public int countWordsInDoc(String docId) {
        return docs.get(docId);
    }

    public int countDocNumber() {
        return docs.size();
    }
}
