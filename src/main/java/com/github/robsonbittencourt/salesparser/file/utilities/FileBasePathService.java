package com.github.robsonbittencourt.salesparser.file.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileBasePathService {

    @Value("${basePath}")
    private String basePath;

    public String getBasePath() {
        if (basePath.equals("")) {
            return System.getProperty("user.home");
        } else {
            return basePath;
        }
    }

}
