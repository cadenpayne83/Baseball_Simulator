import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
public class SimulateGameTest {
    @Test
    public void simulateGame() {
        Team homeTeam = new Team("Home Team");
        Team awayTeam = new Team("Away Team");
        CurrentTeam currentHomeTeam = new CurrentTeam(homeTeam);
        CurrentTeam currentAwayTeam = new CurrentTeam(awayTeam);
        BaseballSimulator.simulateGame(currentHomeTeam, currentAwayTeam);
    }
}
