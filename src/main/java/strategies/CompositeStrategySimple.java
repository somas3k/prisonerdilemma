package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompositeStrategySimple implements PrisonerStrategy {

    private List<PrisonerStrategy> simpleStrategies;

    public CompositeStrategySimple(){
        simpleStrategies = new ArrayList<>();
        simpleStrategies.add(new AlwaysCooperateStrategy());
        simpleStrategies.add(new RandomStrategy());
        simpleStrategies.add(new CopyStrategy());
    }
    @Override
    public Pair<String, PrisonerAction> getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        Random random = new Random();
        return simpleStrategies.get(Math.abs(random.nextInt()%3)).getAction(playerId, history);
    }
}
