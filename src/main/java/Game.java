import org.apache.commons.math3.distribution.GeometricDistribution;
import org.apache.commons.math3.util.Pair;
import prisoner.Prisoner;
import prisoner.PrisonerAction;

import java.util.ArrayList;
import java.util.List;


public class Game {
    private final Prisoner player1;
    private final Prisoner player2;
    private int numberOfRounds;
    private List<Pair<PrisonerAction, PrisonerAction>> history = new ArrayList<>();
    private List<Pair<String, String>> strategyHistory = new ArrayList<>();
    private final GeometricDistribution distribution = new GeometricDistribution(1d / 101);

    public List<Pair<String, String>> getStrategyHistory() {
        return strategyHistory;
    }

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
            Pair<String, PrisonerAction> prisonerAction1 = player1.getStrategy().getAction(1, history);
            Pair<String, PrisonerAction> prisonerAction2 = player2.getStrategy().getAction(2, history);
            strategyHistory.add(Pair.create(prisonerAction1.getFirst(), prisonerAction2.getFirst()));
            history.add(Pair.create(prisonerAction1.getSecond(), prisonerAction2.getSecond()));
        }
    }
}
