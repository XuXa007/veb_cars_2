package org.example.Enums;


public enum Engine {
    GASOLINE ("Бензин", 0),
    DIESEL ("Дизель", 1),
    ELECTRIC ("Электричество", 2),
    HYBRID ("Гибрид", 3);
    private String type;
    private int number;

    Engine(String type, int number) {
        this.type = type;
        this.number = number;
    }
}
