import edu.princeton.cs.algs4.StdOut;

import java.util.Random;
public class Player {
    double battingAverage;
    Team team;

    public Player(double avg, Team team) {
        this.battingAverage = avg;
        this.team = team;
    }

    /* The return value represents the number of bases awarded to the player.
    * e.g. 0 is out, 1 is single, 2 is double, etc. */
    public int hit() {
        double battingAverage =  this.battingAverage * 100;
        Random hitProbability = new Random();
        Random baseDeterminer = new Random();

        // Generate a random number between 0 and 100
        int bound = hitProbability.nextInt(101);

        if (battingAverage > bound) {
            int totalBases = baseDeterminer.nextInt(1, 5);
            System.out.println("Player acquires " + totalBases + " bases!");
            return totalBases;
        }
        System.out.println("Player is out.");
        return 0;
    }
}
