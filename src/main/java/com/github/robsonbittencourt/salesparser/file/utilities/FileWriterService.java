package com.github.robsonbittencourt.salesparser.file.utilities;

import com.github.robsonbittencourt.salesparser.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
@Service
public class FileWriterService {

    public void write(String path, String content) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(System.getProperty("user.home") + path))) {
            buffer.write(content);
        } catch (IOException e) {
            log.error("Error while write in file", e);
            throw new FileException(e.getMessage());
        }
    }

}
