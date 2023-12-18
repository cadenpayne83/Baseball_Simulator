import java.util.Random;

public class BaseballSimulator {

    public static GameState gameState;

    public static void simulateGame(Team homeTeam, Team awayTeam) {
        GameState gameState = new GameState(1, homeTeam, awayTeam, null, null);

        while (gameState.isPlaying()) {
            for (int i = 1; i <= 9; i++) {
                simulateInning();
            }
        }
    }

    public static void simulateInning() {
        while (gameState.getOuts() < 3) {
            simulateAtBat();
        }


    }

    public static void simulateAtBat() {
        Player currentPitcher = gameState.getCurrentPitcher();
        Player currentBatter = gameState.getCurrentBatter();
        int pitchesThrown = 0;
        int strikes = 0;
        int balls = 0;
        while (strikes < 3 || balls < 4) {
            currentPitcher.throwPitch();
        }
    }
}
