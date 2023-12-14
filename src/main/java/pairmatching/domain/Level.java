package pairmatching.domain;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String name;

    Level(String name) {
        this.name = name;
    }

    public static Level inputToLevel(String input) {
        return Arrays.stream(Level.values())
                .filter(level -> level.name.equals(input))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValidValue(String input) {
        for (Level level : Level.values()) {
            if (level.name.equals(input)) {
                return true;
            }
        }
        return false;
    }
}
