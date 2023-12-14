package pairmatching.view;

import java.util.List;
import java.util.StringJoiner;
import pairmatching.domain.Crews;

public class OutputView {
    private static final String PAIR_RESULT_MESSAGE = "%n페어 매칭 결과입니다.%n";
    public static final String PAIR_RESET_MESSAGE = "%n초기화 되었습니다.%n%n";
    public static final String PAIR_RESULT_IS_NULL = "%n페어 매칭 정보가 존재하지 않습니다.%n";

    public void outputInfo() {
        System.out.printf(PromptText.MISSION_INFORMATION.message);
    }

    public void outputPairResult(List<Crews> pairResult) {
        if (pairResult == null) {
            System.out.printf(PAIR_RESULT_IS_NULL);
            return;
        }

        System.out.printf(PAIR_RESULT_MESSAGE);
        for (Crews crews : pairResult) {
            StringJoiner joiner = new StringJoiner(" : ");
            for (String name : crews.getPairs()) {
                joiner.add(name);
            }
            System.out.println(joiner.toString());
        }
        System.out.println();
    }

    public void outputResetPairMessage() {
        System.out.printf(PAIR_RESET_MESSAGE);
    }
}
