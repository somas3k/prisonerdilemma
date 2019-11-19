import prisoner.Prisoner;
import prisoner.PrisonerAction;
import strategies.*;
import org.apache.commons.math3.util.Pair;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void printGame(Game game, PrintStream out) {
        List<Pair<PrisonerAction, PrisonerAction>> gameHistory = game.getHistory();
        List<Pair<String, String>> strategyHistory = game.getStrategyHistory();
        out.println("player" + game.getPlayer1().getId() + ";player" + game.getPlayer2().getId()
                + ";playersum" + game.getPlayer1().getId() + ";playersum" + game.getPlayer2().getId()
                + ";strategy" + game.getPlayer1().getId() + ";strategy" + game.getPlayer2().getId());
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
            out.println(prisonerPoints1 + ";" + prisonerPoints2 + ";" + prisonerTotalPoints1 + ";" + prisonerTotalPoints2 + ";" + strategy1 + ":" + strategy2);
        }
        out.println();
    }

    private static void printGames(List<Game> games) {
        String player1 = games.get(0).getPlayer1().getId().toString();
        String player2 = games.get(0).getPlayer2().getId().toString();
        try (PrintStream out = new PrintStream(player1 + player2 + ".txt")) {
            for (Game game : games) {
                printGame(game, out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printStats(List<Game> games, Prisoner prisoner1, Prisoner prisoner2) {
        int prisonerWins1 = 0;
        int prisonerWins2 = 0;
        for (Game game : games) {
            List<Pair<PrisonerAction, PrisonerAction>> gameHistory = game.getHistory();
            double totalPointsP1 = 0;
            double totalPointsP2 = 0;
            for (Pair<PrisonerAction, PrisonerAction> prisonerActionPrisonerActionPair : gameHistory) {
                Pair<Integer, Integer> points = getPoints(prisonerActionPrisonerActionPair.getFirst(), prisonerActionPrisonerActionPair.getSecond());
                totalPointsP1 += points.getFirst();
                totalPointsP2 += points.getSecond();
            }
            if (totalPointsP1 > totalPointsP2) {
                prisonerWins1++;
            }
            if (totalPointsP2 > totalPointsP1) {
                prisonerWins2++;
            }
        }
        summary(prisoner1, prisoner2, prisonerWins1, prisonerWins2);
    }

    private static void summary(Prisoner prisoner1, Prisoner prisoner2, int prisonerWins1, int prisonerWins2) {
        String s1 = prisoner1.getStrategy().getName();
        String s2 = prisoner2.getStrategy().getName();

        if (prisonerWins1 > prisonerWins2) {
            System.out.println(String.format("%s vs %s, winner is %s; points: %d %d",
                    s1, s2, s1, prisonerWins1, prisonerWins2));
        }
        if (prisonerWins1 < prisonerWins2) {
            System.out.println(String.format("%s vs %s, winner is %s; points: %d %d",
                    s1, s2, s2, prisonerWins1, prisonerWins2));
        }
        if (prisonerWins1 == prisonerWins2) {
            System.out.println(String.format("%s vs %s, draw",
                    s1, s2));
        }
    }

    private static Pair<Integer, Integer> getPoints(PrisonerAction prisonerAction1, PrisonerAction prisonerAction2) {
        int prisonerPoints1 = 0;
        int prisonerPoints2 = 0;

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
        return Pair.create(prisonerPoints1, prisonerPoints2);
    }

    public static void main(String[] args) {
        Prisoner player1 = new Prisoner(1, new RandomStrategy());
        Prisoner player2 = new Prisoner(2, new RevengeStrategy());
        Prisoner player3 = new Prisoner(3, new WSLSStrategy());
        Prisoner player4 = new Prisoner(4, new CompositeStrategySimple());
        Prisoner player5 = new Prisoner(5, new CompositeStrategyComplex());
        List<Prisoner> prisoners = new ArrayList<>();
        prisoners.add(player1);
        prisoners.add(player2);
        prisoners.add(player3);
        prisoners.add(player4);
        prisoners.add(player5);

        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                List<Game> games = new ArrayList<>();
                for (int k = 0; k < 300; k++) {
                    Game game = new Game(prisoners.get(i), prisoners.get(j));
                    game.play();
                    games.add(game);
                }
//                /// wypisze do pliku wszystkie
//                printGames(games);
//                ///wypisze na ekran
//                for (Game game : games) {
//                    printGame(game, System.out);
//                }
                /// wypisuje srednia ilosc punktow zdobytych w 1 turze dla gracza pierwszego i drugiego
                /// wypisuje srednia z punktow zdobytych we wszystkich grach dla gracza pierwszego i drugiego
                printStats(games, prisoners.get(i), prisoners.get(j));
            }
        }
    }
}
