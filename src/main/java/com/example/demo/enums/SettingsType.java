package com.example.demo.enums;

public enum SettingsType {

    RowCount("Row count"),
    ColumnCount("Column count"),
    BombCount("Bomb count");

    private final String value;

    SettingsType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
