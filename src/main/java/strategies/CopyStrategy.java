package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;

public class CopyStrategy implements PrisonerStrategy {
    ///zaczyna kooperacja, potem kopiuje ostatni ruch przeciwnika
    @Override
    public Pair<String, PrisonerAction> getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        PrisonerAction prisonerAction;
        if (history.isEmpty()) {
            prisonerAction = PrisonerAction.COOPERATION;
        } else{
            Pair<PrisonerAction, PrisonerAction> turn = history.get(history.size() - 1);
            prisonerAction = playerId == 1 ? turn.getSecond() : turn.getFirst();
        }
        String strategyName = "CopyStrategy";
        return Pair.create(strategyName, prisonerAction);
    }

    @Override
    public String getName() {
        return "CopyStrategy";
    }
}
