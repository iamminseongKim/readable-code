package cleancode.studycafe.misson;

import cleancode.studycafe.misson.io.ConsoleInputHandler;
import cleancode.studycafe.misson.io.ConsoleOutputHandler;
import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.FileHandler;
import cleancode.studycafe.misson.io.file.StudyCafeLockerPassHandler;
import cleancode.studycafe.misson.io.file.StudyCafePassFileHandler;
import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafeLockerPasses;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePasses;

import java.util.List;

public class StudyCafeApplication {

    public static void main(String[] args) {

        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();

        FileHandler cafePassFileHandler = new StudyCafePassFileHandler();
        FileHandler lockerFileHandler = new StudyCafeLockerPassHandler();

        StudyCafePasses passes =  StudyCafePasses.of((List<StudyCafePass>) cafePassFileHandler.readFileAndMakePasses());
        StudyCafeLockerPasses lockerPasses = StudyCafeLockerPasses.of((List<StudyCafeLockerPass>) lockerFileHandler.readFileAndMakePasses());
        Calculate calculate = new Calculate();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler, passes, lockerPasses, calculate);
        studyCafePassMachine.run();
    }

}
