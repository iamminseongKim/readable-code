package cleancode.studycafe.misson;

import cleancode.studycafe.misson.io.ConsoleInputHandler;
import cleancode.studycafe.misson.io.ConsoleOutputHandler;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.FileHandler;
import cleancode.studycafe.misson.io.file.StudyCafeLockerPassHandler;
import cleancode.studycafe.misson.io.file.StudyCafePassFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        FileHandler cafePassFileHandler = new StudyCafePassFileHandler();
        FileHandler lockerFileHandler = new StudyCafeLockerPassHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, cafePassFileHandler, lockerFileHandler);
        studyCafePassMachine.run();
    }

}
