package com.github.robsonbittencourt.salesparser.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class FileProcess {

    @Autowired
    private FileParser fileParser;

    @Async("async-task-executor")
    public Future<String> process(String path) {
        fileParser.readFile(path);

        return new AsyncResult<String>("done");
    }

}
