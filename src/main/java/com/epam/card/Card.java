package com.epam.card;

public class Card {

    private String value;

    private int point;

    public Card(String value, int point) {
        this.value = value;
        this.point = point;
    }

    public String getValue() {
        return this.value;
    }

    public int getPoint() {
        return this.point;
    }

    public String toString() {
        return value;
    }
}
