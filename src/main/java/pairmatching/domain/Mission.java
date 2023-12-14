package pairmatching.domain;

import static pairmatching.domain.Course.BACKEND;
import static pairmatching.domain.Course.FRONTEND;
import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;

import java.util.Arrays;

public enum Mission {
    BACK_RACING(LEVEL1, BACKEND, "자동차경주"),
    BACK_LOTTO(LEVEL1, BACKEND, "로또"),
    BACK_BASEBALL(LEVEL1, BACKEND, "숫자야구게임"),

    BACK_SHOPPING_BASKET(LEVEL2, BACKEND, "장바구니"),
    BACK_PAYMENT(LEVEL2, BACKEND, "결제"),
    BACK_SUBWAY(LEVEL2, BACKEND,  "지하철노선도"),

    BACK_PERFORMANCE(LEVEL4, BACKEND, "성능개선"),
    BACK_RELEASE(LEVEL4, BACKEND, "배포"),

    FRONT_RACING(LEVEL1, FRONTEND, "자동차경주"),
    FRONT_LOTTO(LEVEL1, FRONTEND, "로또"),
    FRONT_BASEBALL(LEVEL1, FRONTEND, "숫자야구게임"),

    FRONT_SHOPPING_BASKET(LEVEL2, FRONTEND, "장바구니"),
    FRONT_PAYMENT(LEVEL2, FRONTEND, "결제"),
    FRONT_SUBWAY(LEVEL2, FRONTEND, "지하철노선도"),

    FRONT_PERFORMANCE(LEVEL4, FRONTEND, "성능개선"),
    FRONT_RELEASE(LEVEL4, FRONTEND, "배포");

    private final Level level;
    private final Course course;
    private final String name;

    Mission(Level level, Course course, String name) {
        this.level = level;
        this.course = course;
        this.name = name;
    }

    public static Mission inputToMission(String name, Course course) {
        return Arrays.stream(Mission.values())
                .filter(mission -> mission.name.equals(name))
                .filter(mission -> mission.course.equals(course))
                .findFirst()
                .orElse(null);
    }

    public static boolean isValidValue(Level level, String name) {
        for (Mission mission : Mission.values()) {
            if (mission.level.equals(level) && mission.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }
}
