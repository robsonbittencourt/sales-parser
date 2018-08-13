package com.github.robsonbittencourt.salesparser.file.utilities;

import com.github.robsonbittencourt.salesparser.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

@Slf4j
@Service
public class FileReaderService {

    public void readFileLines(String path, Consumer<String> consumer) {
        File file = new File(path);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                consumer.accept(line);
            }
        } catch (IOException e) {
            log.error("Error while reading file", e);
            throw new FileException(e.getMessage());
        }
    }

}
