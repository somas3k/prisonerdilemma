import org.apache.commons.math3.util.Pair;

import java.util.Map;

public interface PrisonerStrategy {
    PrisonerAction getAction(Map<Integer, Pair<PrisonerAction, PrisonerAction>> history);

}
