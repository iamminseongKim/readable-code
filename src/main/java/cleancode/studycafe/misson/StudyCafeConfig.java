package cleancode.studycafe.misson;

import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.FileHandler;
import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafeLockerPasses;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePasses;

import java.util.List;

public class StudyCafeConfig {


    private InputHandler inputHandler;
    private OutputHandler outputHandler;
    private FileHandler cafePassFileHandler;
    private FileHandler lockerFileHandler;
    private Caculate calculate;

    public StudyCafeConfig(InputHandler inputHandler, OutputHandler outputHandler, FileHandler cafePassFileHandler, FileHandler lockerFileHandler, Caculate calculate) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.cafePassFileHandler = cafePassFileHandler;
        this.lockerFileHandler = lockerFileHandler;
        this.calculate = calculate;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public Caculate getCalculate() {
        return calculate;
    }

    public StudyCafePasses getStudyCafePasses() {
        return StudyCafePasses.of((List<StudyCafePass>) cafePassFileHandler.readFileAndMakePasses());
    }

    public StudyCafeLockerPasses getLockerPasses() {
        return StudyCafeLockerPasses.of((List<StudyCafeLockerPass>) lockerFileHandler.readFileAndMakePasses());
    }

}
