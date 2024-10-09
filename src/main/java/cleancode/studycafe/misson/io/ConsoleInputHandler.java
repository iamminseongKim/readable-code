package cleancode.studycafe.misson.io;

import cleancode.studycafe.misson.exception.AppException;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePassType;

import java.util.List;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public StudyCafePassType getPassTypeSelectingUserAction() {
        String userInput = getUserInput();
        return StudyCafePassType.from(userInput);
    }

    private String getUserInput() {
        return SCANNER.nextLine();
    }

    @Override
    public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
        String userInput = getUserInput();
        int selectedIndex = Integer.parseInt(userInput) - 1;
        return passes.get(selectedIndex);
    }

    @Override
    public boolean getLockerSelection() {
        String userInput = getUserInput();
        return "1".equals(userInput);
    }

}
