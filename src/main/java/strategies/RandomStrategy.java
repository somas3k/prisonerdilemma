package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomStrategy implements PrisonerStrategy {

    ///zawsze losowo
    @Override
    public PrisonerAction getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        Random random = new Random();
        return random.nextBoolean() ? PrisonerAction.COOPERATION : PrisonerAction.BETRAYAL;
    }
}
