package edu.mayo.micro.fda.impl.controlller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.server.types.files.AttachedFile;
import io.micronaut.views.View;

import java.util.HashMap;
import java.util.Map;

import edu.mayo.micro.fda.impl.service.FDAValueSets;
import edu.mayo.micro.fda.spreadsheet.FDAExcelService;

@Controller("/") 
public class HomeController {

    protected final FDAValueSets valueSets;
    protected final FDAExcelService vsExcelService;

    public HomeController(FDAValueSets valueSets,  
                          FDAExcelService vsExcelService) {
        this.valueSets = valueSets;
        this.vsExcelService = vsExcelService;
    }

    @View("index") 
    @Get
    Map<String, String> index() {
        return new HashMap<>();
    }

    @Get("/excel") 
    AttachedFile excel() { 
        return vsExcelService.excelFileFromValueSets(valueSets.getAllValues("C130132"));
    }
}