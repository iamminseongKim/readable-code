package cleancode.studycafe.misson;

import cleancode.studycafe.misson.io.ConsoleInputHandler;
import cleancode.studycafe.misson.io.ConsoleOutputHandler;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, studyCafeFileHandler);
        studyCafePassMachine.run();
    }

}
