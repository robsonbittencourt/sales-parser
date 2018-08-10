package com.github.robsonbittencourt.salesparser.file;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.file.parser.DataTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileParser {

    private List<DataAnalisys> posActions;

    public FileParser(List<DataAnalisys> posActions) {
        this.posActions = posActions;
    }

    public void readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String code = extractDataTypeId(line);

                DataType entry = DataTypes.getByCode(code).parseLine(line);

                executePosActions(entry);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.posActions.forEach(p -> p.allEntriesProcessed());
    }

    private String extractDataTypeId(String line) {
        return line.substring(0, 3);
    }

    private void executePosActions(DataType entry) {
        this.posActions.stream().forEach(p -> p.processEntry(entry));
    }

}
