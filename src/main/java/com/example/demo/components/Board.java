package com.example.demo.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.example.demo.enums.GameStatus;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Component
public class Board {

    private SelenideElement backButton = $(By.xpath("//button[text()='Back']")).as("Back button");

    private SelenideElement retryButton = $(By.xpath("//button[text()='Retry']")).as("Retry button");

    private SelenideElement bombsLabel = $(By.xpath("//span[text()='Bombs: ']")).as("Bombs label");

    private SelenideElement markersLabel = $(By.xpath("//span[text()='Markers: ']")).as("Markers left label");

    private SelenideElement statusLabel = $(By.cssSelector(".css-1d0tmsd")).as("Game Status");

    private ElementsCollection markedFiredBombs = $$(By.cssSelector(".css-h4rjx3"));

    private ElementsCollection firedBombs = $$(By.cssSelector(".css-jts58d"));

    private ElementsCollection notTouchedCells = $$(By.cssSelector(".css-1wif00r"));

    private SelenideElement cell(Integer row, Integer col) {
        if (row < 0 || col < 0) {
            throw new RuntimeException("Wrong arguments! Both row and col should be non-negative!");
        }
        return $(By.xpath("//div[@data-column-index=" + col + " and @data-row-index=" + row + "]"))
                .shouldBe(Condition.exist);
    }

    public Integer bombsCount() {
        return Integer.valueOf(bombsLabel.getText().split(": ")[1]);
    }

    public Integer revealedBombsCount() {
        return firedBombs.size() + markedFiredBombs.size();
    }

    public Integer markersLeftCount() {
        return Integer.valueOf(markersLabel.getText().split(": ")[1]);
    }

    public void retry() {
        retryButton.click();
    }

    public void goBack() {
        backButton.click();
    }

    public GameStatus getStatus() {
        return GameStatus.statusOf(statusLabel.getText().toLowerCase());
    }

    /**
     * Method for getting cells which are not being clicked ("new" cells)
     *
     * @return Number of not touched cells
     */
    public Integer getNotTouchedCells() {
        return notTouchedCells.size();
    }

    /**
     * Click at row/col
     *
     * @param row row index, should be non-negative
     * @param col col index, should be non-negative
     * @return -1 if target is a bomb, 0 if empty cell, otherwise return the count of the nearest bombs
     */
    public Integer clickAt(Integer row, Integer col) {
        var cell = cell(row, col);
        cell.click();
        var result = cell.getText();
        return result.equals("✱") ? -1 : result.equals("") ? 0 : Integer.valueOf(result);
    }

    public void rightClickAt(Integer row, Integer col) {
        var cell = cell(row, col);
        cell.contextClick();
    }
}
