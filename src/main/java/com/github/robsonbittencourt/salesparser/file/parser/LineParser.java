package com.github.robsonbittencourt.salesparser.file.parser;

import com.github.robsonbittencourt.salesparser.domain.DataType;

interface LineParser {

    DataType parseLine(String line);

}
