package com.example.demo.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.example.demo.enums.ErrorMessage;
import com.example.demo.enums.SettingsType;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Component
public class Settings {

    private final SettingsBlock rowsSettings = new SettingsBlock(SettingsType.RowCount);

    private final SettingsBlock colsSettings = new SettingsBlock(SettingsType.ColumnCount);

    private final SettingsBlock bombsSettings = new SettingsBlock(SettingsType.BombCount);

    private final SelenideElement startButton = $(By.xpath("//button[text()='Start the game']"));

    public void setUpAndStartGame(Integer rows, Integer cols, Integer bombs){
        this.rowsSettings.setCount(rows);
        this.colsSettings.setCount(cols);
        this.bombsSettings.setCount(bombs);
        this.startGame();
    }

    public SettingsBlock rows() {
        return rowsSettings;
    }

    public SettingsBlock columns() {
        return colsSettings;
    }

    public SettingsBlock bombs() {
        return bombsSettings;
    }

    public void startGame() {
        startButton.click();
    }

    public Boolean hasErrorMessage(ErrorMessage message) {
        return $(By.xpath("//div[text()='" + message.getValue() + "']")).exists();
    }

    public void startButtonShouldBeEnabled() {
        startButton.shouldBe(Condition.enabled);
    }
}
