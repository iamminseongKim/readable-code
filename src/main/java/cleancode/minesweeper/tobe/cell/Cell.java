package cleancode.minesweeper.tobe.cell;

public interface Cell {



    boolean hasLandMineCount();

    CellSnapshot getSnapshot();

    boolean isLandMine(); // 지뢰 기능

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

}
