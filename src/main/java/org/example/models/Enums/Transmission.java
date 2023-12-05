package org.example.models.Enums;


public enum Transmission {
    Manual ("Механичсекая", 0),
    Automatic ("Автоматиическая", 1);
    private String type;
    private int number;

    Transmission(String type, int number) {
        this.type = type;
        this.number = number;
    }
}