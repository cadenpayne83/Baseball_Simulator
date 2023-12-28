import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
public class BasePathTest {
    @Test
    public void testIsHomePlate() {
        Team homeTeam = new Team("Home Team");
        Team awayTeam = new Team("Away Team");
        GameState gameState = new GameState(1, 0, new CurrentTeam(homeTeam), new CurrentTeam(awayTeam)
                , null, null);
        BasePath basePath = new BasePath(gameState);
        BasePath.Base firstBase = basePath.new Base(null);
        BasePath.HomePlate homePlate = basePath.new HomePlate(null);
        assertThat(firstBase.isHomePlate()).isEqualTo(false);
        assertThat(homePlate.isHomePlate()).isEqualTo(true);
    }
}