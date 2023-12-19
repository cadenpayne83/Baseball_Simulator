public class BaseballSimulator {

    public static GameState gameState;

    // Starts a game in inning 1.
    public static void simulateGame(Team homeTeam, Team awayTeam) {
        gameState = new GameState(1, 0, homeTeam, awayTeam, null, null);

        while (gameState.isPlaying()) {
            for (int i = 0; i < 9; i++) {
                simulateInning();
            }
        }
    }

    public static void simulateInning() {
        System.out.println("An inning has begun!");
        System.out.println();
        while (gameState.getOuts() < 3) {
            simulateAtBat();
        }
        System.out.println("The inning has ended!");
        System.out.println();
        gameState.advanceInning();
        gameState.resetOuts();
    }

    public static void simulateAtBat() {
        CurrentPitcher currentPitcher = gameState.getCurrentPitcher();
        Player currentBatter = gameState.getCurrentBatter();
        int pitchesThrownThisAtBat = 0;
        int strikes = 0;
        int balls = 0;
        while ((strikes < 3) && (balls < 4)) {
            boolean isStrike = currentPitcher.throwPitch();

            if (isStrike) {
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
            System.out.println("Ball four, take your base!");
        }
        System.out.println();
    }
}
