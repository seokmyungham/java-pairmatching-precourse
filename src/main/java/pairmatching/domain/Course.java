package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public static boolean isValidValue(String input) {
        for (Course course : Course.values()) {
            if (course.name.equals(input)) {
                return true;
            }
        }
        return false;
    }

    public static Course inputToCourse(String input) {
        return Arrays.stream(Course.values())
                .filter(course -> course.name.equals(input))
                .findFirst()
                .orElse(null);
    }
}
