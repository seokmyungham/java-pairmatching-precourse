package pairmatching.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<String> parseInputByDelimiter(String input, String DELIMITER) {
        return new ArrayList<>(Arrays.asList(input.split(DELIMITER)));
    }
}
