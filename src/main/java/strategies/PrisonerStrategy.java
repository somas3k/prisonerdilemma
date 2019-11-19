package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;

public interface PrisonerStrategy {
    Pair<String, PrisonerAction> getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history);

    String getName();
}
