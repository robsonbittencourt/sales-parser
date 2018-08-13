package com.github.robsonbittencourt.salesparser.file;

import com.github.robsonbittencourt.salesparser.file.utilities.FileBasePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilePathReader {

    @Autowired
    private FileBasePathService fileBasePathService;

    public List<String> datFilesToProcess() {
        List<String> datFilesPaths = new ArrayList<>();

        File directory = new File(fileBasePathService.getBasePath() + "/data/in");

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
