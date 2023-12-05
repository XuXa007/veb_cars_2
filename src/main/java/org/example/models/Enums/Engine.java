package org.example.models.Enums;


public enum Engine {
    Gasoline ("Бензин", 0),
    Diesel ("Дизель", 1),
    Electric ("Электричество", 2),
    Hybrid ("Гибрид", 3);
    private String type;
    private int number;

    Engine(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
