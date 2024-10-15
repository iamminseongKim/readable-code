package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellsTest {


    @Test
    @DisplayName("Cell 일급 컬랙션을 생성한다.")
    void makeCells() throws Exception {
        //given
        Cell[][] cells = new Cell[1][1];
        //when
        Cells from = Cells.from(cells);
        //then
        assertThat(from).isNotNull();
    }

    @Test
    @DisplayName("Cell에 모든 셀이 체크 됐는지 확인한다.")
    void isAllCheckedTest() throws Exception {
        //given
        Cell emptyCell = new EmptyCell();
        Cell numberCell = new NumberCell(1);
        Cell landMineCell  = new LandMineCell();

        emptyCell.open();
        numberCell.open();
        landMineCell.flag();

        List<Cell> cellsList = List.of(emptyCell, numberCell,landMineCell);
        //when
        Cells cells = Cells.of(cellsList);
        //then
        assertThat(cells.isAllChecked()).isTrue();
    }


}
