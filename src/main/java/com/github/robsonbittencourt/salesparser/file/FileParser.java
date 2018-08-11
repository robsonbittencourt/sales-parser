package com.github.robsonbittencourt.salesparser.file;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.file.parser.DataTypes;
import com.github.robsonbittencourt.salesparser.file.utilities.FileDirectoryService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileMoveService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class FileParser {

    @Autowired
    private List<DataAnalisys> posActions;

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    private FileDirectoryService fileDirectoryService;

    @Autowired
    private FileMoveService fileMoveService;

    public void readFile(String path) {
        processLines(path);
        moveProcessedFile(path);
    }

    private void processLines(String path) {
        Consumer<String> consumeLine = (line) -> {
            String code = extractDataTypeId(line);

            DataType entry = DataTypes.getByCode(code).parseLine(line);

            this.posActions.stream().forEach(p -> p.processEntry(entry));
        };

        fileReaderService.readFileLines(path, consumeLine);
    }

    private String extractDataTypeId(String line) {
        return line.substring(0, 3);
    }

    private void moveProcessedFile(String path) {
        File destinationFolder = fileDirectoryService.getDirectory("/data/processed/");

        String fileName = path.replaceAll(System.getProperty("user.home") + "/data/in/", "");
        fileName = LocalDateTime.now().toString() + "-" + fileName;

        fileMoveService.moveFile(path, destinationFolder.getPath() + "/" + fileName);
    }

}
