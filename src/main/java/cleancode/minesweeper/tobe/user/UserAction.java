package cleancode.minesweeper.tobe.user;

public enum UserAction {

    OPEN("셀 오픈"),
    FLAG("깃발 꼽기"),
    UNKNOWN("알수 없음");

    private final String description;

    UserAction(String description) {
        this.description = description;
    }
}
