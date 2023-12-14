package pairmatching.domain;

import java.util.List;

public class Crews {
    private List<String> pairs;

    public Crews(List<String> pairs) {
        this.pairs = pairs;
    }

    public void addCrew(String crew) {
        pairs.add(crew);
    }

    public boolean isPair(Crews otherCrews) {
        List<String> otherPairs = otherCrews.pairs;

        if (otherPairs.size() != this.pairs.size()) {
            return false;
        }

        for (String crew : otherPairs) {
            if (!this.pairs.contains(crew)) {
                return false;
            }
        }

        return true;
    }

    public List<String> getPairs() {
        return pairs;
    }
}