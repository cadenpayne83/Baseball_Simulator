import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Team {
    List<Player> hittingRoster = new ArrayList<>();

    List<Player> pitchingRoster = new ArrayList<>();
    private final String name;

    public Team(String name) {
        this.name = name;
        constructHittingRoster();
    }

    public void constructHittingRoster() {
        // Construct line up.
        Random battingAverageDeterminer = new Random();
        for (int i = 0; i < 9; i++) {
            double battingAverage = battingAverageDeterminer.nextDouble(0.300, 0.500);
            Player player = new Player(battingAverage, 0, this);
            this.hittingRoster.add(player);
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

    public List<CurrentBatter> constructCurrentLineUpFromHittingRoster(CurrentTeam currentTeam) {
        List<CurrentBatter> lineUp = new ArrayList<>();
        for (Player player : this.hittingRoster) {
            lineUp.add(new CurrentBatter(player, player.getBattingAverage(), currentTeam));
        }
        return lineUp;
    }

    public List<CurrentPitcher> constructCurrentPitchingRoster(CurrentTeam currentTeam) {
        List<CurrentPitcher> pitchingRoster = new ArrayList<>();
        for (Player player : this.pitchingRoster) {
            pitchingRoster.add(new CurrentPitcher(player, 0));
        }
        return pitchingRoster;
    }
    public Player getFirstPitcher() {
        return pitchingRoster.get(0);
    }

    public Player getFirstBatter() {
        return hittingRoster.get(0);
    }



}
