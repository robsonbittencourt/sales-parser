package com.github.robsonbittencourt.salesparser.file.utilities;

import com.github.robsonbittencourt.salesparser.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class FileMoveService {

    public void moveFile(String originPath, String destinationPath) {
        try {
            Files.move(Paths.get(originPath), Paths.get(destinationPath));
        } catch (IOException e) {
            log.error("Error while moving file", e);
            throw new FileException(e.getMessage());
        }
    }

}
