package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String SELECT_FUNCTION_PROMPT =
            "기능을 선택하세요.%n1. 페어 매칭%n2. 페어 조회%n3. 페어 초기화%nQ.종료%n";
    private static final String REMATCH_PROMPT = "%n매칭 정보가 있습니다 다시 매칭하시겠습니까?%n네 | 아니오%n";

    public String inputFunctionCode() {
        System.out.printf(SELECT_FUNCTION_PROMPT);
        return Console.readLine();
    }

    public String inputMatchInfo() {
        System.out.printf(PromptText.MISSION_PROMPT.message);
        return Console.readLine();
    }

    public String inputRematchCode() {
        System.out.printf(REMATCH_PROMPT);
        return Console.readLine();
    }
}
