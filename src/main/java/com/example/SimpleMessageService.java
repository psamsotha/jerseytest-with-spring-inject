package com.example;

public class SimpleMessageService implements MessageService {
    private String message = "Hello World";

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
