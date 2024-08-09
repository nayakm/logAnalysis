package com.example.loganalysis;

import java.io.IOException;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;
//import org.json.JSONObject;

@Component
public class OpenAIClient {

	 
	/*The below method gives a possible solution for the particular Error based on Keywords
	 * You can add any keywords and tackle/track those errors easily with Error solution method
	 */
	 public String errorSolution(String errorMessage) {
	        if (errorMessage.toLowerCase().contains("database")) {
	            return "Check database connection settings.";
	        } else if (errorMessage.toLowerCase().contains("network")) {
	            return "Check network connectivity.";
	        } else {
	            return "Refer to the respective api call for further assistance.";
	        }
	    }
	 
	 
	 /*Below is the method when you want AI implementation & add API_KEY*/
//	 public static String generateFix(String errorMessage) throws IOException,InterruptedException {
//		 String prompt = "Error: " + errorMessage +"\nPossible fix:";
//		 JSONObject requestBody = new JSONObject();
//		 requestBody.put("prompt", prompt); 
//		 requestBody.put("max_tokens", 150);
//		 HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openai.com/v1/engines/davinci-codex/completions"))
//				 .header("Content-Type", "application/json") .header("Authorization","Bearer YOUR_API_KEY")
//				 .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString())) .build();
//		 
//		 HttpClient client = HttpClient.newHttpClient(); 
//		 HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//		 return response.body(); 
//		 
//	 }
}

