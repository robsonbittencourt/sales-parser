package com.github.robsonbittencourt.salesparser.file.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileDirectoryService {

    @Autowired
    private FileBasePathService fileBasePathService;

    public File getDirectory(String directoryPath) {
        File destinationFolder = new File(fileBasePathService.getBasePath() + directoryPath);

        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        return destinationFolder;
    }

}
