import org.junit.jupiter.api.Test;

public class SimulateAtBatTest {
    @Test
    public void singleAtBatTest() {
        Team homeTeam = new Team("Home Team");
        Team awayTeam = new Team("Away Team");
//        BaseballSimulator.simulateGame(homeTeam, awayTeam);
    }

    @Test
    public void multipleAtBatTest() {
        Team homeTeam = new Team("Home Team");
        Team awayTeam = new Team("Away Team");

        // Simulate 10 at bats.
        for (int i = 0; i < 10; i++) {
//            BaseballSimulator.simulateGame(homeTeam, awayTeam);
        }
    }
}