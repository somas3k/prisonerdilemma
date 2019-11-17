package strategies;

import org.apache.commons.math3.util.Pair;
import prisoner.PrisonerAction;

import java.util.List;

public class WSLSStrategy implements PrisonerStrategy {

    ///win stay lose swap
    ///zaczynamy kooperujac
    ///wyniki dzielimy na pozytywne dla nas (czyli takie za ktore dosalismy >=3 punkty) i negatywne ( <3 punkty)
    ///jezeli ostatni wynik byl dla nas pozytywny to zostajemy, jak negatywny to zmieniamy

    @Override
    public Pair<String, PrisonerAction> getAction(Integer playerId, List<Pair<PrisonerAction, PrisonerAction>> history) {
        PrisonerAction prisonerAction = null;
        if(history.isEmpty()) prisonerAction = PrisonerAction.COOPERATION;
        else {
            Pair<PrisonerAction, PrisonerAction> lastTurn = history.get(history.size() - 1);
            PrisonerAction myAction = playerId == 1 ? lastTurn.getFirst() : lastTurn.getSecond();
            PrisonerAction enemyAction = playerId == 1 ? lastTurn.getSecond() : lastTurn.getFirst();
            if(myAction.equals(PrisonerAction.COOPERATION)){
                ///jezeli zdradzil to dostalismy 0, wiec zmieniamy
                if(enemyAction.equals(PrisonerAction.BETRAYAL)){
                    prisonerAction = PrisonerAction.BETRAYAL;
                } else{
                    prisonerAction = myAction;
                }
            }
            else if(myAction.equals(PrisonerAction.BETRAYAL)){
                ///jezeli zdradzil to dostalismy 1, wiec zmieniamy
                if(enemyAction.equals(PrisonerAction.BETRAYAL)){
                    prisonerAction = PrisonerAction.COOPERATION;
                } else {
                    prisonerAction = myAction;
                }
            }
        }
        String strategyName = "WSLS";
        return Pair.create(strategyName, prisonerAction);
    }
}
