package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.Beginner;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    @DisplayName("GameBoard를 생성하고 상태를 확인한다.")
    void makeGameBoard() throws Exception {
        //given
        GameLevel gameLevel = new Beginner(); // 8X10, ☼ : 10
        //when
        GameBoard gameBoard = new GameBoard(gameLevel);
        gameBoard.initializeGame();
        //then
        assertThat(gameBoard.getColSize()).isEqualTo(gameLevel.getColSize());
        assertThat(gameBoard.getRowSize()).isEqualTo(gameLevel.getRowSize());
        assertThat(gameBoard.isWinStatus()).isFalse();
        assertThat(gameBoard.isLoseStatus()).isFalse();
        assertThat(gameBoard.isInProgress()).isTrue();
    }
    
    @Test
    @DisplayName("1x1 GameBoard에 지뢰가 0개일때 한셀을 열고 게임을 승리했는지 확인한다.")
    void makeZeroLandMineBoard() throws Exception {
        //given
        GameLevel zeroLandMineLevel = new GameLevel() {
            @Override
            public int getRowSize() {
                return 1;
            }

            @Override
            public int getColSize() {
                return 1;
            }

            @Override
            public int getLandMineCount() {
                return 0;
            }
        };

        CellPosition cellPosition = CellPosition.of(0, 0);

        //when
        GameBoard zeroLandMineBoard = new GameBoard(zeroLandMineLevel);
        zeroLandMineBoard.initializeGame();
        zeroLandMineBoard.openAt(cellPosition);
        CellSnapshot snapshot = zeroLandMineBoard.getSnapshot(cellPosition);
        //then
        assertThat(zeroLandMineBoard.isWinStatus()).isTrue();
        assertThat(snapshot.getStatus()).isEqualTo(CellSnapshotStatus.EMPTY);
    }
    


}
