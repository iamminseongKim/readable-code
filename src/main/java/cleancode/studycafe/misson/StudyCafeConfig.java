package cleancode.studycafe.misson;

import cleancode.studycafe.misson.io.InputHandler;
import cleancode.studycafe.misson.io.OutputHandler;
import cleancode.studycafe.misson.io.file.FileHandler;
import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafeLockerPasses;
import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePasses;
import cleancode.studycafe.misson.pass.PassServiceFactory;

import java.util.List;

public class StudyCafeConfig {


    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler cafePassFileHandler;
    private final FileHandler lockerFileHandler;


    public StudyCafeConfig(InputHandler inputHandler, OutputHandler outputHandler, FileHandler cafePassFileHandler, FileHandler lockerFileHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.cafePassFileHandler = cafePassFileHandler;
        this.lockerFileHandler = lockerFileHandler;
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }


    public StudyCafePasses getStudyCafePasses() {
        return StudyCafePasses.of((List<StudyCafePass>) cafePassFileHandler.readFileAndMakePasses());
    }

    public StudyCafeLockerPasses getLockerPasses() {
        return StudyCafeLockerPasses.of((List<StudyCafeLockerPass>) lockerFileHandler.readFileAndMakePasses());
    }

    public PassServiceFactory getPassServiceFactory() {
        return new PassServiceFactory(getStudyCafePasses(), getLockerPasses());
    }

}
