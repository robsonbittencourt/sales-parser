package com.github.robsonbittencourt.salesparser.file;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.file.utilities.FileDirectoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileProcessScheduler {

    @Autowired
    private FilePathReader pathReader;

    @Autowired
    private FileProcess fileProcess;

    @Autowired
    private FileDirectoryService fileDirectoryService;

    @Autowired
    private List<DataAnalisys> posActions;

    @Scheduled(fixedDelay = 10000)
    public void processPaths() {
        fileDirectoryService.getDirectory("/data");
        fileDirectoryService.getDirectory("/data/in");
        fileDirectoryService.getDirectory("/data/out");

        long initTime = System.currentTimeMillis();

        List<String> paths = pathReader.datFilesToProcess();

        if (paths.isEmpty()) {
            log.info("No files to process.");
            return;
        }

        log.info("Starting file processing. " + paths.size() + " files to be processed.");

        processFiles(paths);

        posActions.forEach(DataAnalisys::allFilesProcessed);

        log.info("End of file processing. Elapsed time: " + (System.currentTimeMillis() - initTime) + " milliseconds.");
    }

    private void processFiles(List<String> paths) {
        List<Future<String>> readFilesFuture = paths.stream().map(p -> fileProcess.process(p)).collect(Collectors.toList());
        while (true) {
            boolean hasFutureNotCompleted = readFilesFuture.stream().anyMatch(r -> !r.isDone());

            if (!hasFutureNotCompleted) {
                break;
            }
        }
    }

}
