package pairmatching.controller;

import static pairmatching.constant.ErrorMessage.CANT_PAIR_MATCH_ERROR_MESSAGE;
import static pairmatching.constant.ProcessCode.FUNCTION_CODE_END;
import static pairmatching.constant.ProcessCode.FUNCTION_CODE_GET_PAIR;
import static pairmatching.constant.ProcessCode.FUNCTION_CODE_MATCH_PAIR;
import static pairmatching.constant.ProcessCode.FUNCTION_CODE_RESET_PAIR;
import static pairmatching.constant.ProcessCode.REMATCH_CODE_NO;

import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Mission;
import pairmatching.sevice.PairService;
import pairmatching.util.InputValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairController {
    private final PairService pairService;
    private final InputView inputView;
    private final OutputView outputView;

    public PairController() {
        this.pairService = new PairService();
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void process() {
        boolean stopProcess = false;
        while (!stopProcess) {
            String functionCode = getFunctionCode();
            processControl(functionCode);
            if (functionCode.equals(FUNCTION_CODE_END)) {
                stopProcess = true;
            }
        }
    }

    public void processControl(String functionCode) {
        if (functionCode.equals(FUNCTION_CODE_MATCH_PAIR)) {
            processMatchPair();
        }
        if (functionCode.equals(FUNCTION_CODE_GET_PAIR)) {
            processGetPair();
        }
        if (functionCode.equals(FUNCTION_CODE_RESET_PAIR)) {
            processResetPair();
        }
    }

    public void processMatchPair() {
        List<String> info = getMatchPair();
        Mission mission = getMission(info);
        if (pairService.getPair(mission) != null) {
            if (getRematchCode().equals(REMATCH_CODE_NO)) {
                processRematchPair();
                return;
            }
        }
        List<Crews> result = pairService.matchPair(mission);
        result = processCheckAlreadyPair(mission, result);
        pairService.storePairHistory(mission);
        outputView.outputPairResult(result);
    }

    private List<Crews> processCheckAlreadyPair(Mission mission, List<Crews> result) {
        int count = 0;
        while (pairService.isAlreadyPair(mission) && count < 3) {
            result = pairService.matchPair(mission);
            count++;
        }
        if (count == 3) {
            throw new IllegalArgumentException(CANT_PAIR_MATCH_ERROR_MESSAGE);
        }
        return result;
    }

    public void processGetPair() {
        List<String> info = getMatchPair();
        Mission mission = getMission(info);
        outputView.outputPairResult(pairService.getPair(mission));
    }

    public void processResetPair() {
        pairService.resetPair();
        outputView.outputResetPairMessage();
    }

    public void processRematchPair() {
        Mission mission = getMission(getRematchPair());
        if (pairService.getPair(mission) != null) {
            if (getRematchCode().equals(REMATCH_CODE_NO)) {
                processMatchPair();
                return;
            }
        }
        List<Crews> result = pairService.matchPair(mission);
        outputView.outputPairResult(result);
    }

    private Mission getMission(List<String> info) {
        Course course = Course.inputToCourse(info.get(0));
        Mission mission = Mission.inputToMission(info.get(2), course);
        return mission;
    }

    private String getFunctionCode() {
        try {
            String functionCode = inputView.inputFunctionCode();
            InputValidator.validateFunctionCodeFormat(functionCode);
            return functionCode;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getFunctionCode();
        }
    }

    private List<String> getMatchPair() {
        try {
            outputView.outputInfo();
            return getRematchPair();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMatchPair();
        }
    }

    private List<String> getRematchPair() {
        try {
            String matchInfo = inputView.inputMatchInfo();
            List<String> info = InputValidator.validateMatchInfoFormat(matchInfo);
            return info;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getRematchPair();
        }
    }

    private String getRematchCode() {
        try {
            String rematchCode = inputView.inputRematchCode();
            InputValidator.validateRematchCodeFormat(rematchCode);
            return rematchCode;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getRematchCode();
        }
    }
}
