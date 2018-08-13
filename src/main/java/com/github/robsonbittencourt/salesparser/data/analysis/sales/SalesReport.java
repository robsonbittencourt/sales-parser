package com.github.robsonbittencourt.salesparser.data.analysis.sales;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.domain.DataType;
import com.github.robsonbittencourt.salesparser.file.utilities.FileBasePathService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileDirectoryService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileReaderService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import java.io.File;

@Service
public class SalesReport implements DataAnalisys {

    private static final String DATA_CONSOLIDATED_DIRECTORY = "/data/consolidated/";

    @Value("${consolidated.data.separator}")
    private String separator;

    @Autowired
    private List<SalesReportItem> reportItems;

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    private FileWriterService fileWriterService;

    @Autowired
    private FileDirectoryService fileDirectoryService;

    @Autowired
    private FileBasePathService fileBasePathService;

    @PostConstruct
    public void initData() {
        fileDirectoryService.getDirectory(DATA_CONSOLIDATED_DIRECTORY);

        for (SalesReportItem reportItem : reportItems) {
            String path = fileBasePathService.getBasePath() + dataConsolidedFileName(reportItem);

            Consumer<String> splitLineContent = l -> reportItem.receiveValues(l.split(separator));

            if (new File(path).exists()) {
                fileReaderService.readFileLines(path, splitLineContent);
            }
        }
    }

    @Override
    public void processEntry(DataType entry) {
        reportItems.stream().forEach(r -> r.process(entry));
    }

    @Override
    public void allFilesProcessed() {
        for (SalesReportItem reportItem : reportItems) {
            String path = dataConsolidedFileName(reportItem);

            fileWriterService.write(path, reportItem.allValues());
        }

        fileWriterService.write(buildFileName(), this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        reportItems.forEach(r -> sb.append(r.description())
                                   .append(separator)
                                   .append(r.value())
                                   .append(System.lineSeparator()));

        return sb.toString();
    }

    private String dataConsolidedFileName(SalesReportItem reportItem) {
        return DATA_CONSOLIDATED_DIRECTORY + reportItem.getClass().getSimpleName().toLowerCase();
    }

    private String buildFileName() {
        return "/data/out/" + LocalDate.now() + ".done.dat";
    }

}
