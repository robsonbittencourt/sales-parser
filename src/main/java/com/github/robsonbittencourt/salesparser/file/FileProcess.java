package com.github.robsonbittencourt.salesparser.file;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisysFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileProcess {

    @Async("async-task-executor")
    public void process(String path) {
        List<DataAnalisys> dataAnalisys = DataAnalisysFactory.get();

        new FileParser(dataAnalisys).readFile(path);
    }

}
