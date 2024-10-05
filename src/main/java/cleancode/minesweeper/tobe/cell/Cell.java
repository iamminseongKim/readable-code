package cleancode.minesweeper.tobe.cell;

public interface Cell {

    String FLAG_SIGN = "⚑";
    String UNCHECKED_SIGN = "□";

    boolean hasLandMineCount();

    String getSign();

    boolean isLandMine(); // 지뢰 기능

    void flag();

    void open();

    boolean isChecked();

    boolean isOpened();

}
