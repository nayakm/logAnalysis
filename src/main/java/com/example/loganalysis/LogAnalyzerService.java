package com.example.loganalysis;

import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class LogAnalyzerService {
    private static final String ERROR_PATTERN = "ERROR -";

    /* Method to find the lines from log file and fetch lines containing the Error_Pattern
     * In this case I have used ERROR_PATTERN = "ERROR -"
     * Below is the sample of Error line
     * ERROR - clientId=consumer-abc.test.enterprise - Database connection error: Database connection failed
     * clientId=consumer-abc.test.enterprise (Content after ERROR_PATTERN till the second hyphen)
     * errorMessage=Database connection error: Database connection failed(Content after second hyphen till end)
     */
    public List<LogError> analyzeLogs(String logFilePath) throws IOException {
    	 List<LogError> errors = new ArrayList<>();
         Pattern pattern = Pattern.compile(ERROR_PATTERN);
         BufferedReader reader = new BufferedReader(new FileReader(logFilePath));
         String line;

         while ((line = reader.readLine()) != null) {
             Matcher matcher = pattern.matcher(line);
             if (matcher.find()) {
            	 int startIndex = matcher.end(); // Position after "ERROR -"
                 
                 int firstHyphenIndex = line.indexOf("-", startIndex);
                 int secondHyphenIndex = line.indexOf('-', firstHyphenIndex + 1);
                 String clientId = line.substring(startIndex, secondHyphenIndex-1).trim();
                 
                 String errorMessage = line.substring(secondHyphenIndex + 1).trim();

                 errors.add(new LogError(clientId, errorMessage));
             }
         }
         reader.close();
         System.out.println("errors="+errors);
         return errors;
    }
}

