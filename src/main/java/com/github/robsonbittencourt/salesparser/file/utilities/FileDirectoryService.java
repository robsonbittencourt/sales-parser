package com.github.robsonbittencourt.salesparser.file.utilities;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileDirectoryService {

    public File getDirectory(String directoryPath) {
        File destinationFolder = new File(System.getProperty("user.home") + directoryPath);

        if (!destinationFolder.exists()) {
            destinationFolder.mkdirs();
        }

        return destinationFolder;
    }

}
