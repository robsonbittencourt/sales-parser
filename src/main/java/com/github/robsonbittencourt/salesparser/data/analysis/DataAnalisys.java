package com.github.robsonbittencourt.salesparser.data.analysis;

import com.github.robsonbittencourt.salesparser.domain.DataType;

public interface DataAnalisys {

    void processEntry(DataType entry);

    void allEntriesProcessed();

}
