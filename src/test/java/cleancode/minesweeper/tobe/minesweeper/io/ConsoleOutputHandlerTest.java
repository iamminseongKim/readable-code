package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.VeryBeginner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleOutputHandlerTest {

    private final PrintStream out = System.out;
    private ByteArrayOutputStream outContent;
    
    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(out);
    }
    
    
    @Test
    @DisplayName("웰컴 메시지를 확인한다.")
    void showGameStartCommentsTest() throws Exception {
        //given
        String message = "지뢰찾기 게임 시작!";
        //when
        ConsoleOutputHandler handler = new ConsoleOutputHandler();
        handler.showGameStartComments();

        //then
        assertThat(outContent.toString()).isNotEmpty();
        assertThat(outContent.toString()).contains(message);
    }

    @Test
    @DisplayName("gameBoard를 만들어 출력이 되는지 확인한다.")
    void showBoardTest() throws Exception {
        //given
        String row = "a b c d e";
        GameBoard gameBoard = new GameBoard(new VeryBeginner());
        gameBoard.initializeGame();
        //when
        ConsoleOutputHandler handler = new ConsoleOutputHandler();
        handler.showBoard(gameBoard);
        //then
        assertThat(outContent.toString()).isNotEmpty();
        assertThat(outContent.toString()).contains(row);
    }
    
    @Test
    @DisplayName("게임 승리 멘트를 확인한다.")
    void showGameWinningCommentTest() throws Exception {
        //given
        String message = "지뢰를 모두 찾았습니다. GAME CLEAR!";
        //when
        ConsoleOutputHandler handler = new ConsoleOutputHandler();
        handler.showGameWinningComment();
        //then
        assertThat(outContent.toString()).contains(message);
    }

    @Test
    @DisplayName("지뢰를 밟았을 때 게임오버 메시지를 확인한다.")
    void showGameLosingCommentTest() throws Exception {
        //given
        String message = "지뢰를 밟았습니다. GAME OVER!";
        //when
        ConsoleOutputHandler handler = new ConsoleOutputHandler();
        handler.showGameLosingComment();
        //then
        assertThat(outContent.toString()).contains(message);
    }

    @Test
    @DisplayName("좌표 입력 안내문을 확인한다.")
    void showCommentForSelectingCellTest() throws Exception {
        //given
        String message = "선택할 좌표를 입력하세요. (예: a1)";
        //when
        ConsoleOutputHandler handler = new ConsoleOutputHandler();
        handler.showCommentForSelectingCell();
        //then
        assertThat(outContent.toString()).contains(message);
    }

    @Test
    @DisplayName("좌표 입력에 따른 사용자 행위 입력 안내문을 확인한다.")
    void showCommentForUserAction() throws Exception {
        //given
        String message = "선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)";
        //when
        ConsoleOutputHandler handler = new ConsoleOutputHandler();
        handler.showCommentForUserAction();
        //then
        assertThat(outContent.toString()).contains(message);
    }



}
