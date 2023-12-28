public class BaseballSimulator {

    public static GameState gameState;

    // Starts a game in inning 1.
    public static void simulateGame(CurrentTeam homeTeam, CurrentTeam awayTeam) {
        gameState = new GameState(1, 0, homeTeam, awayTeam, null, null);

        while (gameState.isPlaying()) {
            simulateInning();
        }
    }

    public static void simulateInning() {
        int i = 0;
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
            i += 1;
            if (i == 20) {
                break;
            }
        }
        System.out.println("The inning has ended!");
        System.out.println();
        gameState.advanceInning();
        gameState.resetOuts();
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

            // At this point, batter only swings at strikes
            if (isStrike) {
                Hit hit = currentBatter.swing();
                int bases = hit.getBases();
                if (bases > 0 && bases < 4) {
                    currentBatter.putOnBase(hit.getBases());
                    return;
                } else if (bases == 4) {
                    CurrentTeam battersTeam = currentBatter.getAssociatedCurrentTeam();

                    // change to account for runners on base
                    BasePath basePath = gameState.getBasePath();
                    int numberOfRunnersOnBase = basePath.numberOfRunnersOnBase();

                    gameState.incrementScore(1 + numberOfRunnersOnBase);
                    System.out.println("Home run! Score is " + gameState.getHomeTeamRuns() + " to " + gameState.getAwayTeamRuns());
                    // next batter now
                    System.out.println();
                    return;
                }
                strikes += 1;
                System.out.println("Strike " + strikes + "!");
            } else {
                balls += 1;
                System.out.println("Ball " + balls + ".");
            }

            pitchesThrownThisAtBat += 1;
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
