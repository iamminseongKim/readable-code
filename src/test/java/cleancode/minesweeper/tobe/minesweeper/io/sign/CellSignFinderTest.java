package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellSignFinderTest {

    private static final String EMPTY_SIGN = "■";
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";


    @Test
    @DisplayName("지뢰셀 스냅샷을 만들어서 지뢰 마크가 나오는지 확인한다.")
    void findCellSignFromLandMineCellSnapShot() throws Exception {
        //given
        CellSnapshot landMineSnapshot = CellSnapshot.ofLandMine();
        CellSignFinder cellSignFinder = new CellSignFinder();
        //when
        String landMineCellSign = cellSignFinder.findCellSignFrom(landMineSnapshot);
        //then
        assertThat(landMineCellSign).isEqualTo(LAND_MINE_SIGN);
    }

    @Test
    @DisplayName("깃발셀 스냅샷을 만들어서 깃발 마크가 나오는지 확인한다.")
    void findCellSignFromFlagCellSnapShot() throws Exception {
        //given
        CellSnapshot flagSnapshot = CellSnapshot.ofFlag();
        CellSignFinder cellSignFinder = new CellSignFinder();
        //when
        String flagCellSign = cellSignFinder.findCellSignFrom(flagSnapshot);
        //then
        assertThat(flagCellSign).isEqualTo(FLAG_SIGN);
    }

    @Test
    @DisplayName("빈셀 스냅샷을 만들어서 빈 마크가 나오는지 확인한다.")
    void findCellSignFromEmptyCellSnapShot() throws Exception {
        //given
        CellSnapshot emptySnapshot = CellSnapshot.ofEmpty();
        CellSignFinder cellSignFinder = new CellSignFinder();
        //when
        String emptyCellSign = cellSignFinder.findCellSignFrom(emptySnapshot);
        //then
        assertThat(emptyCellSign).isEqualTo(EMPTY_SIGN);
    }

    @Test
    @DisplayName("미확인 셀 스냅샷을 만들어서 미확인 마크가 나오는지 확인한다.")
    void findCellSignFromUncheckedCellSnapShot() throws Exception {
        //given
        CellSnapshot uncheckedSnapshot = CellSnapshot.ofUnchecked();
        CellSignFinder cellSignFinder = new CellSignFinder();
        //when
        String uncheckedCellSign = cellSignFinder.findCellSignFrom(uncheckedSnapshot);
        //then
        assertThat(uncheckedCellSign).isEqualTo(UNCHECKED_SIGN);
    }

    @Test
    @DisplayName("숫자셀 스냅샷을 만들어서 문자열 숫자가 나오는지 확인한다.")
    void findCellSignFromNumberCellSnapShot() throws Exception {
        //given
        CellSnapshot numberSnapshot = CellSnapshot.ofNumber(1);
        CellSignFinder cellSignFinder = new CellSignFinder();
        //when
        String uncheckedCellSign = cellSignFinder.findCellSignFrom(numberSnapshot);
        //then
        assertThat(uncheckedCellSign).isEqualTo("1");
    }
    
    @Test
    @DisplayName("그 외의 고려되지 않은 셀 스냅샷은 오류를 발생시킨다.")
    void findCellSignFromException() throws Exception {
        //given
        CellSnapshot errorCellSnapshot = CellSnapshot.of(null, 0);
        CellSignFinder cellSignFinder = new CellSignFinder();
        //when
        //then
        assertThatThrownBy(() -> cellSignFinder.findCellSignFrom(errorCellSnapshot))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("확인할 수 없는 셀입니다.");
    }
    




}
