package pairmatching.util;

import static pairmatching.constant.ErrorMessage.IS_NOT_VALID_INPUT_ERROR_MESSAGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class InputValidator {
    private static final List<String> FUNCTION_CODE = new ArrayList<>(Arrays.asList("1", "2", "3", "Q"));
    private static final List<String> REMATCH_CODE = new ArrayList<>(Arrays.asList("네", "아니오"));

    public static void validateFunctionCodeFormat(String input) {
        if (!FUNCTION_CODE.contains(input)) {
            throw new IllegalArgumentException(IS_NOT_VALID_INPUT_ERROR_MESSAGE);
        }
    }

    public static void validateRematchCodeFormat(String input) {
        if (!REMATCH_CODE.contains(input)) {
            throw new IllegalArgumentException(IS_NOT_VALID_INPUT_ERROR_MESSAGE);
        }
    }

    public static List<String> validateMatchInfoFormat(String input) {
        List<String> info = InputParser.parseInputByDelimiter(input, ", ");

        if (!Course.isValidValue(info.get(0)) ||
                !Level.isValidValue(info.get(1)) ||
                !Mission.isValidValue(Level.inputToLevel(info.get(1)), info.get(2))) {
            throw new IllegalArgumentException(IS_NOT_VALID_INPUT_ERROR_MESSAGE);
        }

        return info;
    }
}
