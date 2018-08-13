package com.github.robsonbittencourt.salesparser.file.utilities;

import com.github.robsonbittencourt.salesparser.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@Service
public class FileWriterService {

    @Autowired
    private FileBasePathService fileBasePathService;

    public void write(String path, String content) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(fileBasePathService.getBasePath() + path))) {
            buffer.write(content);
        } catch (IOException e) {
            log.error("Error while write in file", e);
            throw new FileException(e.getMessage());
        }
    }

}
