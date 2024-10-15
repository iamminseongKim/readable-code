package cleancode.minesweeper.tobe.minesweeper.board.cell;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CellSnapshotTest {

    @Test
    @DisplayName("셀 스냅샷을 만들어서 셀 상태를 기록한다.")
    void makeCellSnapshot() throws Exception {
        //given
        CellSnapshot emptySnapshot = CellSnapshot.ofEmpty();
        CellSnapshot numberSnapshot = CellSnapshot.ofNumber(1);
        CellSnapshot landMineSnapshot = CellSnapshot.ofLandMine();
        //when

        //then
        assertThat(emptySnapshot.isSameStatus(numberSnapshot.getStatus())).isFalse();
        assertThat(emptySnapshot.isSameStatus(emptySnapshot.getStatus())).isTrue();
        assertThat(numberSnapshot.getNearbyLandMineCount()).isEqualTo(1);
        assertThat(landMineSnapshot.getStatus()).isEqualTo(CellSnapshotStatus.LAND_MINE);
    }

}
