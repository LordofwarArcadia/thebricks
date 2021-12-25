package com.example.demo;

import com.example.demo.components.Board;
import com.example.demo.components.Settings;
import com.example.demo.enums.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SettingsTests {

    @Autowired
    Settings settings;

    @Autowired
    Board board;

    @BeforeEach
    public void setUp() {
        open("http://localhost:3000/");
    }

    @Test
    public void checkBombsCountShouldBeLessThenBoardSize() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(24);

        assertThat(settings.hasErrorMessage(ErrorMessage.BombCountShouldBeLess)).isFalse();
    }

    @Test
    public void checkAllInputsArePositiveNumbers() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isFalse();
    }

    @Test
    public void checkSettingsShouldBeSavedAfterStartingGameAndPressingBack() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(5);

        settings.startGame();

        board.goBack();

        assertThat(settings.rows().getCount()).isEqualTo(5);
        assertThat(settings.columns().getCount()).isEqualTo(5);
        assertThat(settings.bombs().getCount()).isEqualTo(5);
    }

    //region Rows/Cols size should be less 10000
    @Test
    public void checkRowsShouldBeLessThan10000ButMore() {
        settings.rows().setCount(10001);
        settings.columns().setCount(5);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.BoardSizeShouldBeLess)).isTrue();
    }

    @Test
    public void checkRowsShouldBeLessThan10000ButEqual() {
        settings.rows().setCount(1000);
        settings.columns().setCount(5);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.BoardSizeShouldBeLess)).isFalse();
        //Bug - less than  10000 means 9999 and less, in this case Message is wrong or the logic should approve 9999 and less
    }

    @Test
    public void checkColsShouldBeLessThan10000ButMore() {
        settings.rows().setCount(5);
        settings.columns().setCount(10001);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.BoardSizeShouldBeLess)).isTrue();
    }

    @Test
    public void checkColsShouldBeLessThan10000ButEqual() {
        settings.rows().setCount(5);
        settings.columns().setCount(10000);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.BoardSizeShouldBeLess)).isFalse();
    }
    //endregion

    //region Bombs count validity tests
    @Test
    public void checkBombsCountShouldBeLessThenBoardSizeButEqual() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(25);

        assertThat(settings.hasErrorMessage(ErrorMessage.BombCountShouldBeLess)).isTrue();
    }

    @Test
    public void checkBombsCountShouldBeLessThenBoardSizeButMore() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(26);

        assertThat(settings.hasErrorMessage(ErrorMessage.BombCountShouldBeLess)).isTrue();
    }
    //endregion

    //region Rows/Cols/Bombs should be positive
    @Test
    public void checkRowsShouldBePositiveButIsZero() {
        settings.rows().setCount(0);
        settings.columns().setCount(5);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isTrue();
    }

    @Test
    public void checkRowsShouldBePositiveButIsNegative() {
        settings.rows().setCount(-1);
        settings.columns().setCount(5);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isTrue();
    }

    @Test
    public void checkColsShouldBePositiveButIsZero() {
        settings.rows().setCount(5);
        settings.columns().setCount(0);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isTrue();
    }

    @Test
    public void checkColsShouldBePositiveButIsNegative() {
        settings.rows().setCount(5);
        settings.columns().setCount(-1);
        settings.bombs().setCount(5);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isTrue();
    }

    @Test
    public void checkBombsShouldBePositiveButIsZero() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(0);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isTrue();
    }

    @Test
    public void checkBombsShouldBePositiveButIsNegative() {
        settings.rows().setCount(5);
        settings.columns().setCount(5);
        settings.bombs().setCount(-1);

        assertThat(settings.hasErrorMessage(ErrorMessage.InputsShouldBePositive)).isTrue();
    }
    //endregion

}
