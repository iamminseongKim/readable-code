package cleancode.studycafe.misson.model;

import java.util.ArrayList;
import java.util.List;

public class StudyCafePasses {

    private final List<StudyCafePass> passes;

    private StudyCafePasses(List<StudyCafePass> passes) {
        this.passes = passes;
    }

    public static StudyCafePasses of(List<StudyCafePass> passes) {
        return new StudyCafePasses(passes);
    }

    public List<StudyCafePass> matchedTypeMadePasses(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> passesCopy = new ArrayList<>(passes);

        return passesCopy.stream()
                .filter(pass -> pass.getPassType() == studyCafePassType)
                .toList();
    }
}
