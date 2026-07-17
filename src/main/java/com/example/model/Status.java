package com.example.model;

public enum Status {
    TO_DO("[ ]"),
    DONE("[X]");

    private final String symbol;

    Status(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}