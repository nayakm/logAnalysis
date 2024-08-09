package com.example.loganalysis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class ErrorDocument {

    	@Autowired
    	private final OpenAIClient openAI;
    	
    	public ErrorDocument(OpenAIClient openAI) {
    		this.openAI=openAI;
    	}
        public void documentErrors(List<LogError> errors, String outputFilePath) throws IOException {
        	 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

             for (LogError error : errors) {
                 String possibleFix = openAI.errorSolution(error.errorMessage);
                 writer.write("Error occured in " + error.clientId + "\n");
                 writer.write("Error Message: " + error.errorMessage + "\n");
                 writer.write("Possible Fix: " + possibleFix + "\n\n");
        }
             writer.close();
    }
    
}