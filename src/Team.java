import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Team {
    List<Player> lineUp = new ArrayList<>();

    List<Player> pitchingRoster = new ArrayList<>();
    private final String name;

    public Team(String name) {
        this.name = name;
    }

    public void constructTeam() {
        // Construct line up.
        Random battingAverageDeterminer = new Random();
        for (int i = 0; i < 9; i++) {
            double battingAverage = battingAverageDeterminer.nextDouble(0.100, 0.500);
            Player player = new Player(battingAverage, 0, this);
            this.lineUp.add(player);
        }

        // Construct pitching roster.
        Random eraDeterminer = new Random();
        for (int i = 0; i < 5; i++) {
            double era = eraDeterminer.nextDouble(1.0, 5.0);
            Player player = new Player(0, era, this);
            this.pitchingRoster.add(player);
        }

        // Sort pitchers by era in descending order. compareTo method
        // located in the PLayer class.
        Collections.sort(pitchingRoster);
    }



    public Player getFirstPitcher() {
        return pitchingRoster.get(0);
    }

    public Player getFirstBatter() {
        return lineUp.get(0);
    }



}
