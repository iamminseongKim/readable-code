package cleancode.studycafe.misson.model;

import cleancode.studycafe.misson.exception.AppException;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    private final String description;


    private static final String HOURY_INPUT = "1";
    private static final String WEEKLY_INPUT = "2";
    private static final String FIXED_INPUT = "3";


    StudyCafePassType(String description) {
        this.description = description;
    }

    public static StudyCafePassType from(String userInput) {

        if (HOURY_INPUT.equals(userInput)) {
            return HOURLY;
        }

        if (WEEKLY_INPUT.equals(userInput)) {
            return WEEKLY;
        }

        if (FIXED_INPUT.equals(userInput)) {
            return FIXED;
        }
        throw new AppException("잘못된 입력입니다.");
    }
}
