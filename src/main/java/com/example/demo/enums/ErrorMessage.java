package com.example.demo.enums;

public enum ErrorMessage {

    InputsShouldBePositive("All inputs should be positive"),
    BombCountShouldBeLess("Bomb count should be less than rows * columns"),
    BoardSizeShouldBeLess("Board size should be less than 10000");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
