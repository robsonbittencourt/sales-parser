package com.github.robsonbittencourt.salesparser.file;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilePathReader {

    public List<String> datFilesToProcess() {
        List<String> datFilesPaths = new ArrayList<>();

        File directory = new File(System.getProperty("user.home")+"/data/in");

        File[] files = directory.listFiles();

        for (File file : directory.listFiles()) {
            if (isDatFile(file)) {
                datFilesPaths.add(file.getAbsolutePath());
            }
        }

        return datFilesPaths;
    }

    private boolean isDatFile(File file) {
        return file.isFile() && file.getName().endsWith(".dat");
    }

}
