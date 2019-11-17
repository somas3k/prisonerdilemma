package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.ArrayList;
import java.util.List;

public class CompositeStrategyComplex implements PrisonerStrategy {

    private Integer counter;
    private List<PrisonerStrategy> complexStrategies;

    public CompositeStrategyComplex(){
        complexStrategies = new ArrayList<>();
        complexStrategies.add(new RevengeStrategy());
        complexStrategies.add(new WSLSStrategy());
        counter = 0;
    }

    @Override
    public Pair<String, PrisonerAction> getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        return counter++%2 == 0 ? complexStrategies.get(0).getAction(playerId, history) : complexStrategies.get(1).getAction(playerId, history);
    }
}
