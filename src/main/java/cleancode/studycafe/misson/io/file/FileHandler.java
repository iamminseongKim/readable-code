package cleancode.studycafe.misson.io.file;

import java.util.List;

public interface FileHandler {

    String SPLIT_COMMA = ",";

    List<?> readFileAndMakePasses();

}
