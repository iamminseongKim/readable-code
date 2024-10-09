package cleancode.studycafe.misson.io.file;

import java.util.List;

public interface FileHandler {

    public static final String SPLIT_COMMA = ",";

    public List<?> readFileAndMakePasses();

}
