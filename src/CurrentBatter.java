import java.util.Random;

public class CurrentBatter {

    private Player player;
    private double battingAverage;

    public CurrentBatter(Player player, double battingAverage) {
        this.battingAverage = battingAverage;
    }

    public Hit swing() {
        Random chanceOfHit = new Random();
        double hitChance = chanceOfHit.nextDouble(101);
        double battingAverageAsPercent = this.battingAverage * 100;
        if (hitChance < battingAverageAsPercent) {
            // Calculate hit
            int totalBases = chanceOfHit.nextInt(5);
            return new Hit(totalBases);
        }
        return new Hit(0);
    }
}
