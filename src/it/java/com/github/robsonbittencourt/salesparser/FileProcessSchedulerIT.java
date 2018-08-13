package com.github.robsonbittencourt.salesparser;

import com.github.robsonbittencourt.salesparser.exception.FileException;
import com.github.robsonbittencourt.salesparser.file.FileProcessScheduler;
import com.github.robsonbittencourt.salesparser.file.utilities.FileBasePathService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileDirectoryService;
import com.github.robsonbittencourt.salesparser.file.utilities.FileWriterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("it-tests")
public class FileProcessSchedulerIT {

    @Autowired
    private FileDirectoryService fileDirectoryService;

    @Autowired
    private FileWriterService fileWriterService;

    @Autowired
    private FileProcessScheduler fileProcessScheduler;

    @Autowired
    private FileBasePathService fileBasePathService;

    {
        // Code block to reset contents before Spring Context loads
        String basePath = new File("src/it/resources").getAbsolutePath();
        deleteDir(new File(basePath + "/data"));
    }

    @Test
    public void shoudlProcessFileCorrectly() {
        String inFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/test.dat");
        fileWriterService.write("/data/in/test.dat", inFile);

        fileProcessScheduler.processPaths();

        File[] inFiles = new File(fileBasePathService.getBasePath() + "/data/in").listFiles();
        assertEquals(0, inFiles.length);

        String outFile = readFileContent(fileBasePathService.getBasePath() + "/data/out/" + LocalDate.now() + ".done.dat");
        String expectedOutFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/expected-out-file");
        assertEquals(expectedOutFile, outFile);

        String processedFile = readFileContent(new File(fileBasePathService.getBasePath() + "/data/processed/").listFiles()[0].getAbsolutePath());
        String expectedProcessedFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/expected-processed-file");
        assertEquals(expectedProcessedFile, processedFile);

        String customerQuantityFile = readFileContent(fileBasePathService.getBasePath() + "/data/consolidated/customerquantity");
        String expectedCustomerQuantityFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/expected-customerquantity");
        assertEquals(customerQuantityFile, expectedCustomerQuantityFile);

        String moreExpensiveSaleFile = readFileContent(fileBasePathService.getBasePath() + "/data/consolidated/moreexpensivesale");
        String expectedMoreExpensiveSaleFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/expected-moreexpensivesale");
        assertEquals(moreExpensiveSaleFile, expectedMoreExpensiveSaleFile);

        String salesmanQuantityFile = readFileContent(fileBasePathService.getBasePath() + "/data/consolidated/salesmanquantity");
        String expectedSalesmanQuantityFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/expected-salesmanquantity");
        assertEquals(salesmanQuantityFile, expectedSalesmanQuantityFile);

        String worseSaleValueFile = readFileContent(fileBasePathService.getBasePath() + "/data/consolidated/worsesalevalue");
        String expectedWorseSaleValueFile = readFileContent(new File("src/it/resources").getAbsolutePath() + "/expected-worsesalevalue");
        assertEquals(worseSaleValueFile, expectedWorseSaleValueFile);
    }

    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    private String readFileContent(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
            StringBuilder sb = new StringBuilder();

            br.lines().forEach(l -> sb.append(l).append(System.lineSeparator()));

            return sb.toString();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }

}
