package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;

public class CopyStrategy implements PrisonerStrategy {

    ///zaczyna kooperacja, potem kopiuje ostatni ruch przeciwnika
    @Override
    public PrisonerAction getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        if(history.isEmpty()) return PrisonerAction.COOPERATION;
        Pair<PrisonerAction, PrisonerAction> turn = history.get(history.size() - 1);
        return (3-playerId) == 2 ? turn.getSecond() : turn.getFirst();
    }
}
