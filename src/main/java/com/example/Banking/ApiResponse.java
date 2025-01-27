package com.example.Banking;

public class ApiResponse {

    private String message;
    private Double data;

    public ApiResponse(String message, Double data) {
        this.message = message;
        this.data = data;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }
}
