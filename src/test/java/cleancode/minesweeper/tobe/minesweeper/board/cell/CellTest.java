package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    
    @Test
    @DisplayName("숫자셀을 생성하고 초기 상태를 확인한다.")
    void makeNumberCell() throws Exception {
        //given
        int nearbyLandMineCount = 1;
        CellSnapshotStatus unchecked = CellSnapshotStatus.UNCHECKED;
        //when
        Cell numberCell = new NumberCell(nearbyLandMineCount);
        //then
        assertThat(numberCell.getSnapshot().getStatus()).isEqualTo(unchecked);
        assertThat(numberCell.isLandMine()).isFalse();
        assertThat(numberCell.hasLandMineCount()).isTrue();
        assertThat(numberCell.isOpened()).isFalse();
    }

    @Test
    @DisplayName("빈 셀을 만들고 초기 상태를 확인한다.")
    void makeEmptyCell() throws Exception {
        //given
        CellSnapshotStatus unchecked = CellSnapshotStatus.UNCHECKED;
        //when
        Cell emptyCell = new EmptyCell();
        //then
        assertThat(emptyCell.getSnapshot().getStatus()).isEqualTo(unchecked);
        assertThat(emptyCell.isLandMine()).isFalse();
        assertThat(emptyCell.hasLandMineCount()).isFalse();
        assertThat(emptyCell.isOpened()).isFalse();
    }

    @Test
    @DisplayName("지뢰 셀을 만들고 초기 상태를 확인한다.")
    void makeLandMineCell() throws Exception {
        //given
        CellSnapshotStatus unchecked = CellSnapshotStatus.UNCHECKED;
        //when
        Cell landMineCell = new LandMineCell();
        //then
        assertThat(landMineCell.getSnapshot().getStatus()).isEqualTo(unchecked);
        assertThat(landMineCell.isLandMine()).isTrue();
        assertThat(landMineCell.hasLandMineCount()).isFalse();
        assertThat(landMineCell.isOpened()).isFalse();
    }

    @Test
    @DisplayName("숫자셀을 열고 이에 따른 상태를 확인한다.")
    void openNumberCell() throws Exception {
        //given
        int nearbyLandMineCount = 1;
        CellSnapshotStatus status = CellSnapshotStatus.NUMBER;

        //when
        Cell numberCell = new NumberCell(nearbyLandMineCount);
        numberCell.open();

        //then
        assertThat(numberCell.getSnapshot().getNearbyLandMineCount()).isEqualTo(nearbyLandMineCount);
        assertThat(numberCell.getSnapshot().getStatus()).isEqualTo(status);
        assertThat(numberCell.isLandMine()).isFalse();
        assertThat(numberCell.hasLandMineCount()).isTrue();
        assertThat(numberCell.isOpened()).isTrue();
    }

    @Test
    @DisplayName("지뢰셀에 flag를 꼽고 상태를 확인한다.")
    void flagLandMineCell() throws Exception {
        //given
        CellSnapshotStatus flag = CellSnapshotStatus.FLAG;

        //when
        Cell landMineCell = new LandMineCell();
        landMineCell.flag();
        //then
        assertThat(landMineCell.getSnapshot().getStatus()).isEqualTo(flag);
        assertThat(landMineCell.isLandMine()).isTrue();
        assertThat(landMineCell.hasLandMineCount()).isFalse();
        assertThat(landMineCell.isChecked()).isTrue();
        assertThat(landMineCell.isOpened()).isFalse();

    }

}
