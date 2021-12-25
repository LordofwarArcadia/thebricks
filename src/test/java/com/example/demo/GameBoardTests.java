package com.example.demo;

import com.codeborne.selenide.Configuration;
import com.example.demo.components.Board;
import com.example.demo.components.Settings;
import com.example.demo.enums.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GameBoardTests {

    @Autowired
    Settings settings;

    @Autowired
    Board board;

    @BeforeEach
    public void setUp() {
        open("http://localhost:3000/");
    }

    @Test
    /*
     * The logic of this is pretty simple: 100*100=10000 cells filled with 9999 bombs
     * so there is only one cell left (10000-9999).
     * Without this rule the chance of clicking to empty cell is 1/10000
     */
    public void checkFirstClickAlwaysGood() {
        settings.setUpAndStartGame(100, 100, 9999);

        var bombsNear = board.clickAt(1, 1);

        assertThat(board.getStatus()).isEqualByComparingTo(GameStatus.InProgress);
        assertThat(bombsNear).isEqualTo(8);
    }

    @Test
    public void checkIfFailAllBombsShouldBeRevealed(){
        settings.setUpAndStartGame(5,5,24);

        board.clickAt(0,0);
        board.rightClickAt(0,1);
        board.clickAt(0,2);

        assertThat(board.revealedBombsCount()).isEqualTo(24);
    }

    @Test
    public void checkBombAndMarkersLabelsShouldBeEqualAtStart() {
        settings.setUpAndStartGame(10, 10, 99);

        assertThat(board.bombsCount()).isEqualTo(99);
        assertThat(board.markersLeftCount()).isEqualTo(99);
    }

    @Test
    public void checkWinStatusAndLabels() {
        settings.setUpAndStartGame(1, 2, 1);

        board.clickAt(0, 0);
        board.rightClickAt(0, 1);

        assertThat(board.getStatus()).isEqualByComparingTo(GameStatus.Victory);
        assertThat(board.bombsCount()).isEqualTo(1);
        assertThat(board.markersLeftCount()).isEqualTo(0);
    }

    @Test
    public void checkLooseStatusAndLabels() {
        settings.setUpAndStartGame(1, 2, 1);

        board.clickAt(0, 0);
        var failClickResult = board.clickAt(0, 1);

        assertThat(failClickResult).isEqualTo(-1);
        assertThat(board.getStatus()).isEqualByComparingTo(GameStatus.Lost);
        assertThat(board.bombsCount()).isEqualTo(1);
        assertThat(board.markersLeftCount()).isEqualTo(1);
    }

    @Test
    public void checkRetryShouldResetTheBoardAfterFail(){
        settings.setUpAndStartGame(1,2,1);

        board.clickAt(0,0);
        board.clickAt(0,1);
        board.retry();

        assertThat(board.getNotTouchedCells()).isEqualTo(2);
    }

    @Test
    public void checkRetryShouldResetTheBoardWhileInProgress(){
        settings.setUpAndStartGame(1,2,1);

        board.clickAt(0,0);
        board.retry();

        assertThat(board.getNotTouchedCells()).isEqualTo(2);
    }
}
