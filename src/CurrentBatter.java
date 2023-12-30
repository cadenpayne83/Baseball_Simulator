import java.util.Random;

public class CurrentBatter {

    private Player player;
    private double battingAverage;
    private CurrentTeam associatedCurrentTeam;

    public CurrentBatter(Player player, double battingAverage, CurrentTeam team) {
        this.battingAverage = battingAverage;
        this.player = player;
        this.associatedCurrentTeam = team;
    }

    // Batter attempts a swing.
    public Hit swing() {
        Random chanceOfHit = new Random();
        // MLB averages about 11% of whiffing on a swing
        int whiffChance = chanceOfHit.nextInt(0, 100);
        if (whiffChance <= 11) {
            return new Hit(-1);
        }
        // Ball has been hit and it in play. Average batting average
        // on balls put in play (BABIP) in the MLB is .300.
        System.out.println("Ball in play!");
        double BABIPchance = chanceOfHit.nextDouble(0, 1.0);

        if (BABIPchance <= .300) {
            // Ball is not an out
            // Calculate hit

            // Calculate chance of hitting a home run
            // About 16% of hits are home runs in the MLB
            double homeRunChance = chanceOfHit.nextDouble(0, 1.0);
            if (homeRunChance <= 0.16) {
                return new Hit(4);
            } else { // This executes if there is not a home run.
                int totalBases = chanceOfHit.nextInt(1, 4);
                return new Hit(totalBases);
            }

        }

        System.out.println("Ball is caught! Batter out!");
        return new Hit(0);
    }

    // Can call putOnBase on a current batter, but needs to call
    // a putOnBase method in GameState to actually alter the base path.
    public void putOnBase(int baseNumber) {
        if (baseNumber == 1) {
            BaseballSimulator.gameState.single(this);
        } else if (baseNumber == 2) {
            BaseballSimulator.gameState.double_(this);
        } else if (baseNumber == 3) {
            BaseballSimulator.gameState.triple(this);
        } else if (baseNumber == 4) {
            BaseballSimulator.gameState.homeRun(this);
        }
        System.out.println();
    }

    public Player getAssociatedPlayer() {
        return this.player;
    }

    public CurrentTeam getAssociatedCurrentTeam() {
        return this.associatedCurrentTeam;
    }

    public void walk() {
        BaseballSimulator.gameState.walk(this);
    }
}
