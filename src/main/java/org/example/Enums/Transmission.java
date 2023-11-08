package org.example.Enums;


public enum Transmission {
    MANUAL ("Механичсекая", 0),
    AUTOMATIC ("Автоматиическая", 1);
    private String type;
    private int number;

    Transmission(String type, int number) {
        this.type = type;
        this.number = number;
    }
}