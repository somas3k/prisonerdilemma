import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.util.Pair;
import prisoner.Prisoner;
import prisoner.PrisonerAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Map<Prisoner, Integer> points = new HashMap<>();
    private final GeometricDistribution distribution = new GeometricDistribution(1d / 101);
    private final Prisoner player1;
    private final Prisoner player2;
    private int numberOfRounds;
    private List<Pair<PrisonerAction, PrisonerAction>> history = new ArrayList<>();

    public Prisoner getPlayer1() {
        return player1;
    }

    public Prisoner getPlayer2() {
        return player2;
    }

    public List<Pair<PrisonerAction, PrisonerAction>> getHistory() {
        return history;
    }

    Game(Prisoner player1, Prisoner player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    void play() {
        numberOfRounds = distribution.sample();
        for (int round = 1; round <= numberOfRounds; round++) {
            PrisonerAction prisonerAction1 = player1.getStrategy().getAction(1, history);
            PrisonerAction prisonerAction2 = player2.getStrategy().getAction(2, history);
            calculateAndSavePointsForActions(prisonerAction1, prisonerAction2);
            history.add(round, Pair.create(prisonerAction1, prisonerAction2));
        }
    }

    public void reset() {
        points.clear();
        numberOfRounds = 0;
    }

    Map<Prisoner, Integer> getPoints() {
        return points;
    }

    int getNumberOfRounds() {
        return numberOfRounds;
    }

    private void calculateAndSavePointsForActions(PrisonerAction prisonerAction1, PrisonerAction prisonerAction2) {
        int prisonerPoints1 = 0;
        int prisonerPoints2 = 0;

        if (prisonerAction1 == PrisonerAction.COOPERATION) {
            if (prisonerAction2 == PrisonerAction.COOPERATION) {
                prisonerPoints1 = prisonerPoints2 = 3;
            } else {
                prisonerPoints2 = 5;
            }
        } else {
            if (prisonerAction2 == PrisonerAction.BETRAYAL) {
                prisonerPoints1 = prisonerPoints2 = 1;
            } else {
                prisonerPoints1 = 5;
            }
        }
        points.merge(player1, prisonerPoints1, Integer::sum);
        points.merge(player2, prisonerPoints2, Integer::sum);
    }

}
