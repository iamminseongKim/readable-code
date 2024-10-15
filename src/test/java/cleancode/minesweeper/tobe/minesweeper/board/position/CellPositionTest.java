package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {

    @Test
    @DisplayName("셀 포지션이 정상적으로 만들어지는지 확인한다.")
    void cellPosition() throws Exception {
        //given
        int rowIndex = 10;
        int colIndex = 10;

        //when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);

        //then
        assertThat(cellPosition.getColIndex()).isEqualTo(colIndex);
        assertThat(cellPosition.getRowIndex()).isEqualTo(rowIndex);
    }
    
    @Test
    @DisplayName("셀의 좌표값은 음이 아닌 정수다.")
    void makeZeroCellPosition() throws Exception {
        //given
        //when
        //then
        assertThatThrownBy(() -> CellPosition.of(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");

        assertThatThrownBy(() -> CellPosition.of(-1, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

}
