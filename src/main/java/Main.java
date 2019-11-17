import prisoner.Prisoner;
import prisoner.PrisonerAction;
import strategies.*;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void printGame(Game game) {
        List<Pair<PrisonerAction, PrisonerAction>> gameHistory = game.getHistory();
        List<Pair<String, String>> strategyHistory = game.getStrategyHistory();
        System.out.println("player" + game.getPlayer1().getId() + ";player" + game.getPlayer2().getId()
                + ";playersum" + game.getPlayer1().getId() + ";playersum" + game.getPlayer2().getId()
                +";strategy" + game.getPlayer1().getId() + ";strategy" + game.getPlayer2().getId());
        int prisonerTotalPoints1 = 0;
        int prisonerTotalPoints2 = 0;
        for (int i = 0; i < gameHistory.size(); i++) {
            int prisonerPoints1 = 0;
            int prisonerPoints2 = 0;
            PrisonerAction prisonerAction1 = gameHistory.get(i).getFirst();
            PrisonerAction prisonerAction2 = gameHistory.get(i).getSecond();
            String strategy1 = strategyHistory.get(i).getFirst();
            String strategy2 = strategyHistory.get(i).getSecond();
            if (prisonerAction1 == PrisonerAction.COOPERATION) {
                if (prisonerAction2 == PrisonerAction.COOPERATION) {
                    prisonerPoints1 = prisonerPoints2 = 3;
                } else {
                    prisonerPoints2 = 5;
                }
            } else {
                if (prisonerAction2 == PrisonerAction.BETRAYAL) {
                    prisonerPoints1 = prisonerPoints2 = 1;
                } else {
                    prisonerPoints1 = 5;
                }
            }
            prisonerTotalPoints1 += prisonerPoints1;
            prisonerTotalPoints2 += prisonerPoints2;
            System.out.println(prisonerPoints1 + ";" + prisonerPoints2 + ";" + prisonerTotalPoints1 + ";" + prisonerTotalPoints2 + ";" + strategy1 + ":" + strategy2);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Prisoner player1 = new Prisoner(1, new RandomStrategy());
        Prisoner player2 = new Prisoner(2, new RevengeStrategy());
        Prisoner player3 = new Prisoner(3, new WSLSStrategy());
        Prisoner player4 = new Prisoner(4, new CompositeStrategySimple());
        Prisoner player5 = new Prisoner(5, new CompositeStrategyComplex());
        List<Prisoner> prisoners = new ArrayList<>();
        List<Game> games = new ArrayList<>();
        prisoners.add(player1);
        prisoners.add(player2);
        prisoners.add(player3);
        prisoners.add(player4);
        prisoners.add(player5);
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                Game game = new Game(prisoners.get(i), prisoners.get(j));
                game.play();
                games.add(game);
            }
        }
        for (Game game : games) {
            printGame(game);
        }
    }
}
