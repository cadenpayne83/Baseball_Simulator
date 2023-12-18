import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
    List<Player> lineUp = new ArrayList<>();
    private final String name;

    public Team(String name) {
        this.name = name;
    }

    public void constructTeam() {
        Random battingAverageDeterminer = new Random();
        for (int i = 0; i < 9; i++) {
            double battingAverage = battingAverageDeterminer.nextDouble(0.100, 0.500);
            Player player = new Player(battingAverage, this);
            this.lineUp.add(player);
        }
    }
}
