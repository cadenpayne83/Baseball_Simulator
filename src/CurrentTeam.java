import java.util.ArrayList;
import java.util.List;

public class CurrentTeam {

    private Team associatedTeam;

    private List<CurrentBatter> lineUp;

    public CurrentTeam(Team team) {
        this.associatedTeam = team;
        this.lineUp = new ArrayList<>();

        // Construct line up.
        constructLineUp();
    }

    public Team getAssociatedTeam() {
        return this.associatedTeam;
    }

    public void constructLineUp() {
        for (Player player : associatedTeam.hittingRoster) {
            lineUp.add(new CurrentBatter(player, player.getBattingAverage(), this));
        }
    }

    public CurrentBatter getFirstBatter() {
        return lineUp.get(0);
    }

    public CurrentPitcher getFirstPitcher() {
        return new CurrentPitcher(associatedTeam.pitchingRoster.get(0), 0);
    }
}
