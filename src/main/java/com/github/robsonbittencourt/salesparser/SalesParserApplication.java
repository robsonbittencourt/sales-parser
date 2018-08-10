package com.github.robsonbittencourt.salesparser;

import com.github.robsonbittencourt.salesparser.data.analysis.DataAnalisys;
import com.github.robsonbittencourt.salesparser.data.analysis.sales.SalesReport;
import com.github.robsonbittencourt.salesparser.file.FileParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SalesParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesParserApplication.class, args);

		List<DataAnalisys> dataAnalisys = new ArrayList<>();
		dataAnalisys.add(new SalesReport());

		new FileParser(dataAnalisys).readFile("/home/robson/teste.dat");
	}
}
