package cleancode.studycafe.misson.io.file;

import cleancode.studycafe.misson.model.StudyCafePass;
import cleancode.studycafe.misson.model.StudyCafePassType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StudyCafePassFileHandler implements FileHandler {

    private static final String STUDY_CAFE_PASS_LIST_CSV = "src/main/resources/cleancode/studycafe/pass-list.csv";

    @Override
    public List<StudyCafePass> readFileAndMakePasses() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(STUDY_CAFE_PASS_LIST_CSV));
            List<StudyCafePass> studyCafePasses = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(SPLIT_COMMA);
                StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
                int duration = Integer.parseInt(values[1]);
                int price = Integer.parseInt(values[2]);
                double discountRate = Double.parseDouble(values[3]);

                StudyCafePass studyCafePass = StudyCafePass.of(studyCafePassType, duration, price, discountRate);
                studyCafePasses.add(studyCafePass);
            }

            return studyCafePasses;
        } catch (IOException e) {
            throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
        }
    }
}
