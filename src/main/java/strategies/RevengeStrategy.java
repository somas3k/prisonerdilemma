package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;

public class RevengeStrategy implements PrisonerStrategy {

    ///zawsze kooperuje, jezeli przeciwnik zdradzil chociaz raz to zawsze zdradza
    @Override
    public PrisonerAction getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        for (Pair<PrisonerAction, PrisonerAction> turn : history) {
            if ((playerId == 1 ? turn.getSecond() : turn.getFirst()) == PrisonerAction.BETRAYAL)
                return PrisonerAction.BETRAYAL;
        }
        return PrisonerAction.COOPERATION;
    }
}
