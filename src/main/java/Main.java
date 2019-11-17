import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Prisoner player1 = new Prisoner(1, new AlwaysCooperateStrategy());
        Prisoner player2 = new Prisoner(2, new AlwaysCooperateStrategy());
        Game game = new Game(player1, player2);
        game.play();
        Map<Prisoner, Integer> points = game.getPoints();
        System.out.println("Number of rounds : " + game.getNumberOfRounds());
        System.out.println("Prisoner 1 : " + points.get(player1));
        System.out.println("Prisoner 2 : " + points.get(player2));
        game.reset();
    }
}
