package com.example.loganalysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogAnalyzerController {

	
    private final LogAnalyzerService logAnalyzerService;
    private final ErrorDocument errorDocument;

   @Autowired
    public LogAnalyzerController(LogAnalyzerService logAnalyzerService,ErrorDocument errorDocument) {
        this.logAnalyzerService = logAnalyzerService;
        this.errorDocument =errorDocument ;
    }
   
   @Value("${log.file.path}")
   private String logFilePath;

   @Value("${output.file.path}")
   private String outputFilePath;

    @RequestMapping("/analyze")
    public String analyzeLogs() {
        try {
        	System.out.println("logFilePath="+logFilePath);
        	List<LogError> errors = logAnalyzerService.analyzeLogs(logFilePath);
        	errorDocument.documentErrors(errors, outputFilePath);
            return "Error analysis completed.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during analysis: " + e.getMessage();
        }
    }

   
}
