import java.util.Random;

public class BaseballSimulator {

    public static GameState gameState;

    // Starts a game in inning 1.
    public static void simulateGame(CurrentTeam homeTeam, CurrentTeam awayTeam) {
        gameState = new GameState(1, 0, homeTeam, awayTeam, null, null);

        while (gameState.isPlaying()) {
            simulateInning();
        }

        System.out.println("Game Over! Final score is home team " + gameState.getHomeTeamRuns() +
                ", away team " + gameState.getAwayTeamRuns());
    }

    public static void simulateInning() {
        int inning = gameState.getInning();
        boolean isTopOfInning = gameState.getIsTopOfInning();
        if (isTopOfInning) {
            System.out.print("Top of the ");
        } else {
            System.out.print("Bottom of the ");
        }

        // This deals with 1st, 2nd, 3rd, 4th and so on
        if (inning == 1) {
            System.out.println("1st!");
        } else if (inning == 2) {
            System.out.println("2nd!");
        } else if (inning == 3) {
            System.out.println("3rd!");
        } else {
            System.out.println(inning + "th!");
        }

        System.out.println();
        while (gameState.getOuts() < 3) {
            gameState.getBasePath().announceRunners();
            simulateAtBat();
        }
        System.out.println("The inning has ended!");
        System.out.println();
        gameState.advanceInning();
        gameState.resetOuts();
        gameState.resetRunners();
    }

    public static void simulateAtBat() {
        System.out.println("Batter Up!");
        CurrentPitcher currentPitcher = gameState.getCurrentPitcher();
        CurrentBatter currentBatter = gameState.getCurrentBatter();
        int pitchesThrownThisAtBat = 0;
        int strikes = 0;
        int balls = 0;
        while ((strikes < 3) && (balls < 4)) {
            boolean isStrike = currentPitcher.throwPitch();
            // Can't find stat, but I'm assuming mlb batters swing at about
            // half of pitches in the strike zone.
            Random chanceToSwingAtBall = new Random();
            int chance = chanceToSwingAtBall.nextInt(2); // 0 or 1
            Hit hit;

            // At this point, batter will not swing at balls.
            if (isStrike) {

                if (chance == 1) { // this executes if the batter attempted a swing.

                    hit = currentBatter.swing();
                    int bases = hit.getBases();

                    if (bases == -1) { // If hit is -1, it's a swing and a miss.
                        strikes += 1;
                        System.out.println("Swing and a miss! strike " + strikes);
                    } else if (bases == 0) { // If hit is 0, ball was caught in play
                        gameState.advanceOuts();
                        return;
                    } else if (bases > 0 && bases < 4) {
                        currentBatter.putOnBase(hit.getBases());
                        return;
                    } else if (bases == 4) {
                        currentBatter.putOnBase(hit.getBases());
                        return;

                    }
                } else { // this executes if batter did not attempt a swing
                    strikes += 1;
                    System.out.println("Strike looking! Strike " + strikes + "!");
                }
            } else if (!isStrike) {
                balls += 1;
                System.out.println("Ball! Ball " + balls);
            }
        }

        if (strikes == 3) {
            System.out.println("Strike three, you're out!");
            gameState.advanceOuts();
        } else if (balls == 4) {
            currentBatter.walk();
        }
        System.out.println();
    }


}
