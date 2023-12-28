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

    public Hit swing() {
        Random chanceOfHit = new Random();
        double hitChance = chanceOfHit.nextDouble(101);
        double battingAverageAsPercent = this.battingAverage * 100;
        if (hitChance < battingAverageAsPercent) {
            // Calculate hit
            int totalBases = chanceOfHit.nextInt(1, 4);
            return new Hit(totalBases);
        }
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
