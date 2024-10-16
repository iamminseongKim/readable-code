package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConsoleInputHandlerTest {

    private static final String OPEN_INPUT = "1\n";
    private static final String FLAG_INPUT = "2\n";
    private static final String UNKNOWN_INPUT = "3\n";
    private static final String CELL_POSITION_INPUT = "a1\n"; // 예시로 'a1'을 입력으로 설정
    private static final String WRONG_ENG_CELL_POSITION_INPUT = "A1\n"; // 65
    private static final String WRONG_NUM_CELL_POSITION_INPUT = "a0\n";

    @Test
    @DisplayName("콘솔 입력이 1일 때 OPEN ENUM을 리턴한다.")
    void getOpenUserActionTest() throws Exception {
        //given
        System.setIn(new ByteArrayInputStream(OPEN_INPUT.getBytes()));
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        //when
        UserAction userActionFromUser = consoleInputHandler.getUserActionFromUser();
        //then
        assertThat(userActionFromUser).isEqualTo(UserAction.OPEN);
    }

    @Test
    @DisplayName("콘솔 입력이 2일 때 FLAG ENUM을 리턴한다.")
    void getFlagUserActionTest() throws Exception {
        //given
        System.setIn(new ByteArrayInputStream(FLAG_INPUT.getBytes()));
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        //when
        UserAction userActionFromUser = consoleInputHandler.getUserActionFromUser();
        //then
        assertThat(userActionFromUser).isEqualTo(UserAction.FLAG);
    }

    @Test
    @DisplayName("콘솔 입력이 1 또는 2가 아닐 때 UNKNOWN ENUM을 리턴한다.")
    void getUnknownUserActionTest() throws Exception {
        //given
        System.setIn(new ByteArrayInputStream(UNKNOWN_INPUT.getBytes()));
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        //when
        UserAction userActionFromUser = consoleInputHandler.getUserActionFromUser();
        //then
        assertThat(userActionFromUser).isEqualTo(UserAction.UNKNOWN);
    }

    @Test
    @DisplayName("좌표를 입력 받아 CellPosition으로 변경한다.")
    void getCellPositionsTest() throws Exception {
        //given
        System.setIn(new ByteArrayInputStream(CELL_POSITION_INPUT.getBytes()));
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        //when
        CellPosition cellPositionFromUser = consoleInputHandler.getCellPositionFromUser();
        //then
        assertThat(cellPositionFromUser.getColIndex()).isEqualTo(0);
        assertThat(cellPositionFromUser.getRowIndex()).isEqualTo(0);
    }

    @Test
    @DisplayName("좌표의 영문 값이 a(97)미만이면 오류가 발생한다.")
    void getWrongEngCellPositionTest() throws Exception {
        //given
        System.setIn(new ByteArrayInputStream(WRONG_ENG_CELL_POSITION_INPUT.getBytes()));
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        //when
        //then
        assertThatThrownBy(consoleInputHandler::getCellPositionFromUser)
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력 입니다.");
    }


    @Test
    @DisplayName("좌표의 숫자 값이 1미만이면 오류가 발생한다.")
    void getWrongCellPositionTest() throws Exception {
        //given
        System.setIn(new ByteArrayInputStream(WRONG_NUM_CELL_POSITION_INPUT.getBytes()));
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        //when
        //then
        assertThatThrownBy(consoleInputHandler::getCellPositionFromUser)
                .isInstanceOf(GameException.class)
                .hasMessage("잘못된 입력 입니다.");
    }




}
