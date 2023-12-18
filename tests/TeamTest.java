import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
public class TeamTest {
    @Test
    public void constructTeam() {
        Random battingAverageDeterminer = new Random();
        Team team = new Team();
        team.constructTeam();

        System.out.println("Line up has " + team.lineUp.size() + " players!");
    }
}
