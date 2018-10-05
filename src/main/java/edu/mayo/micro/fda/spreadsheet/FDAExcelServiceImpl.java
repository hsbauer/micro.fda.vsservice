package edu.mayo.micro.fda.spreadsheet;

import edu.mayo.micro.fda.model.FDAValueSet;
import io.micronaut.http.server.types.files.AttachedFile;
import builders.dsl.spreadsheet.builder.api.Stylesheet;
import builders.dsl.spreadsheet.builder.poi.PoiSpreadsheetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Singleton 
public class FDAExcelServiceImpl implements FDAExcelService {

    private static final Logger LOG = LoggerFactory.getLogger(FDAExcelServiceImpl.class);

    @SuppressWarnings("unchecked")
	@Override
    public AttachedFile excelFileFromValueSets(List<FDAValueSet> valueSets) {
        try {
            File file = File.createTempFile(HEADER_EXCEL_FILENAME , HEADER_EXCEL_FILE_SUFIX);
            PoiSpreadsheetBuilder.create(file).build(w -> {
                w.apply ( (Class<? extends Stylesheet>) FDAExcelStylesheet.class);
                w.sheet(SHEET_NAME, s -> {
                    s.row(r -> Stream.of(HEADER_SOURCE, 
                    		HEADER_NCIT_SUBSET_CODE, 
                    		HEADER_FDA_SUBSET_NAME,
                    		HEADER_NCIT_CONCEPT_CODE,
                    		HEADER_FDA_SOURCE_PT,
                    		HEADER_FDA_SOURCE_SYN,
                    		HEADER_FDA_SOURCE_DEF,
                    		HEADER_NCIT_SOURCE_DEF)
                            .forEach(header -> r.cell(cd -> {
                                    cd.value(header);
                                    cd.style(FDAExcelStylesheet.STYLE_HEADER);
                                })
                            ));
                    valueSets.stream()
                            .forEach( vs -> s.row(r -> {
                                r.cell(vs.getSource());
                                r.cell(vs.getNCISubsetCode());
                                r.cell(vs.getFDASubsetName());
                                r.cell(vs.getNCItConceptCode());
                                r.cell(vs.getFDASubsetName());
                                r.cell(vs.getFDASourcePT());
                                r.cell(vs.getFDASourceSynonyms());
                                r.cell(vs.getFDASourceDefinition());
                                r.cell(vs.getNCItDefinition());
                            }));
                });
            });
            return new AttachedFile(file, HEADER_EXCEL_FILENAME);
        } catch (IOException e) {
            if (LOG.isErrorEnabled()) {
                LOG.error("File not found exception raised when generating excel file");
            }
        }
        return null;
    }

}