package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;

public class AlwaysCooperateStrategy implements PrisonerStrategy {
    ///zawsze kooperuje
    @Override
    public PrisonerAction getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        return PrisonerAction.COOPERATION;
    }
}
