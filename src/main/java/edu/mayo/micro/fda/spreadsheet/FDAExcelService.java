package edu.mayo.micro.fda.spreadsheet;

import edu.mayo.micro.fda.model.FDAValueSet;
import java.util.List;
import io.micronaut.http.server.types.files.AttachedFile;

public interface FDAExcelService {
    static final String SHEET_NAME = "FDA";
    static final String HEADER_SOURCE = "Source";
    static final String HEADER_NCIT_SUBSET_CODE = "NCIt Subset Code";
    static final String HEADER_FDA_SUBSET_NAME = "FDA Subset Name";
    static final String HEADER_NCIT_CONCEPT_CODE = "NCIt Concept Code";
    static final String HEADER_FDA_SOURCE_PT = "FDA Source PT";
    static final String HEADER_FDA_SOURCE_SYN = "FDA Source Synonyms(s)";
    static final String HEADER_FDA_SOURCE_DEF = "FDA Source Definition";
    static final String HEADER_NCIT_SOURCE_DEF = "NCIt Definition";
    static final String HEADER_EXCEL_FILE_SUFIX = ".xlsx";
    static final String HEADER_EXCEL_FILE_PREFIX = "FDA";
    static final String HEADER_EXCEL_FILENAME = HEADER_EXCEL_FILE_PREFIX + HEADER_FDA_SUBSET_NAME + HEADER_EXCEL_FILE_SUFIX;

    AttachedFile excelFileFromValueSets(List<FDAValueSet> valueSets); 
}