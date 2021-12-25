package com.example.demo.enums;

import java.util.Arrays;

public enum GameStatus {
    InProgress("waiting for your move"),
    Lost("you lost"),
    Victory("victory");

    private String message;

    GameStatus(String message) {
        this.message = message;
    }

    public static GameStatus statusOf(String text) {
        return Arrays.stream(values())
                .filter(x -> x.message.equals(text))
                .findAny()
                .orElseThrow();
    }
}
