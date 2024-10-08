package cleancode.studycafe.misson.io.file;

import cleancode.studycafe.misson.model.StudyCafeLockerPass;
import cleancode.studycafe.misson.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafeLockerPassHandler implements FileHandler{

    private static final String STUDY_CAFE_LOCKER_CSV = "src/main/resources/cleancode/studycafe/locker.csv";

    @Override
    public List<StudyCafeLockerPass> readFileAndMakePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(STUDY_CAFE_LOCKER_CSV));
            List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(SPLIT_COMMA);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);

                StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(studyCafePassType, duration, price);
                lockerPasses.add(lockerPass);
            }

            return lockerPasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
