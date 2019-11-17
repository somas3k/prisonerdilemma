import org.apache.commons.math3.util.Pair;

import java.util.Map;

public class AlwaysCooperateStrategy implements PrisonerStrategy {
    @Override
    public PrisonerAction getAction(Map<Integer, Pair<PrisonerAction, PrisonerAction>> history) {
        return PrisonerAction.COOPERATION;
    }
}
