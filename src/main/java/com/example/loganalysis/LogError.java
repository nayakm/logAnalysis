package com.example.loganalysis;

public class LogError {
    String clientId;
    String errorMessage;

    public LogError(String clientId, String errorMessage) {
        this.clientId = clientId;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Client ID: " + clientId + "\nError Message: " + errorMessage ;
    }
}
