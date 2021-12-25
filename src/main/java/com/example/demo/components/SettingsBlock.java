package com.example.demo.components;

import com.codeborne.selenide.SelenideElement;
import com.example.demo.enums.SettingsType;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SettingsBlock {

    private String blockName;

    private SelenideElement label;

    private SelenideElement input;

    /**
     * Init new block of settings of types: Row, Column or Bomb.
     * @param type Type of Settings (Row, Column or Bomb)
     */
    public SettingsBlock(SettingsType type) {
        this.blockName = type.getValue();
        this.label = $(By.xpath("//label[text()='" + blockName + "']"));
        this.input = label.$(By.xpath("./input"));
    }

    public void setCount(Integer value){
        input.setValue(value.toString());
    }

    public Integer getCount(){
        return Integer.valueOf(input.getValue());
    }
}
