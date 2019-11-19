package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;

public class AlwaysCooperateStrategy implements PrisonerStrategy {
    @Override
    public Pair<String, PrisonerAction> getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        return Pair.create(getName(), PrisonerAction.COOPERATION);
    }

    @Override
    public String getName() {
        return "AlwaysCooperate";
    }
}
