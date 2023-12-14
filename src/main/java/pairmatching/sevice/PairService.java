package pairmatching.sevice;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.domain.Course;
import pairmatching.domain.Crews;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;

public class PairService {
    private Map<Mission, List<Crews>> result = new HashMap<>();
    private final Map<Level, List<Crews>> isPaired = new HashMap<>();

    public List<Crews> matchPair(Mission mission) {
        List<String> crewNames = getNamesList(createFilePath(mission.getCourse()));
        List<String> shuffledCrew = Randoms.shuffle(crewNames);
        initializeStore(mission);
        matchPairsEven(mission, shuffledCrew);
        if (isCrewsOdd(shuffledCrew)) {
            String last = shuffledCrew.get(shuffledCrew.size() - 1);
            result.get(mission).get(shuffledCrew.size() / 2 - 1).addCrew(last);
        }

        return getPair(mission);
    }

    private boolean isCrewsOdd(List<String> shuffledCrew) {
        return shuffledCrew.size() % 2 != 0;
    }

    private void matchPairsEven(Mission mission, List<String> shuffledCrew) {
        for (int i = 0; i < shuffledCrew.size() - 1; i+=2) {
            String a = shuffledCrew.get(i);
            String b = shuffledCrew.get(i + 1);
            Crews newCrews = new Crews(new ArrayList<>(Arrays.asList(a, b)));
            result.get(mission).add(newCrews);
        }
    }

    private void initializeStore(Mission mission) {
        result.put(mission, new ArrayList<>());
        isPaired.put(mission.getLevel(), new ArrayList<>());
    }

    public boolean isAlreadyPair(Mission mission) {
        for (Crews crews : result.get(mission)) {
            return isPaired.get(mission.getLevel()).stream()
                    .anyMatch(pairHistory -> pairHistory.isPair(crews));
        }
        return false;
    }

    public void storePairHistory(Mission mission) {
        isPaired.put(mission.getLevel(), result.get(mission));
    }

    public List<Crews> getPair(Mission mission) {
        return result.get(mission);
    }

    public void resetPair() {
        result = new HashMap<>();
    }

    public List<String> getNamesList(String path) {
        List<String> namesList = new ArrayList<>();
        try {
            namesList  = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return namesList;
    }

    public String createFilePath(Course course) {
        String path = "";
        if (course.equals(Course.BACKEND)) {
            path = "C:\\Users\\hello\\Desktop\\test\\java-pairmatching-precourse\\src\\main\\resources\\backend-crew.md";
        }
        if (course.equals(Course.FRONTEND)) {
            path = "C:\\Users\\hello\\Desktop\\test\\java-pairmatching-precourse\\src\\main\\resources\\frontend-crew.md";
        }
        return path;
    }
}
