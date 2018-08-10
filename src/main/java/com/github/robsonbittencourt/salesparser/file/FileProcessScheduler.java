package com.github.robsonbittencourt.salesparser.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileProcessScheduler {

    @Autowired
    private FilePathReader pathReader;

    @Autowired
    private FileProcess fileProcess;

    @Scheduled(fixedDelay = 10000)
    public void processPaths() {
        List<String> paths = pathReader.datFilesToProcess();

        paths.stream().forEach(p -> fileProcess.process(p));
    }

}
