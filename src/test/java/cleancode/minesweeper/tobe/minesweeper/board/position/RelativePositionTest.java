package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RelativePositionTest {


    @Test
    @DisplayName("RelativePosition를 정상적으로 생성한다.")
    void makeRelativePosition() throws Exception {
        //given
        int deltaRow = 1;
        int deltaCol = -1;
        //when
        RelativePosition position = RelativePosition.of(deltaRow, deltaCol);
        //then
        assertThat(deltaRow).isEqualTo(position.getDeltaRow());
        assertThat(deltaCol).isEqualTo(position.getDeltaCol());
    }

    @Test
    @DisplayName("셀 주변 체크 객체 리스트를 정상적으로 생성한다.")
    void makeSurroundedPositions() throws Exception {
        //given
        List<RelativePosition> expectedPositions = List.of(
                RelativePosition.of(-1, -1),
                RelativePosition.of(-1, 0),
                RelativePosition.of(-1, 1),
                RelativePosition.of(0, -1),
                RelativePosition.of(0, 1),
                RelativePosition.of(1, -1),
                RelativePosition.of(1, 0),
                RelativePosition.of(1, 1)
        );
        //when
        List<RelativePosition> surroundedPositions = RelativePosition.SURROUNDED_POSITIONS;

        //then
        assertThat(surroundedPositions).isEqualTo(expectedPositions);
    }


}
