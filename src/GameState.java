import java.util.LinkedList;

public class GameState {
    private int inning;

    private boolean topOfInning;
    private int outs;
    private Player currentPitcher;
    private Player currentBatter;
    private Team homeTeam;
    private int homeTeamRuns;
    private Team awayTeam;
    private int awayTeamRuns;
    private boolean isPlaying;
    private BasePath basePath;

    public GameState(int inning, int outs, Team homeTeam, Team awayTeam, Player currentPitcher, Player currentBatter) {
        this.inning = inning;
        this.outs = 0;
        this.homeTeam = homeTeam;
        this.homeTeamRuns = 0;
        this.awayTeam = awayTeam;
        this.awayTeamRuns = 0;
        this.isPlaying = true;
        this.topOfInning = true;

        // Construct teams in order to grab players.
        this.homeTeam.constructTeam();
        this.awayTeam.constructTeam();

        // Grab current pitcher and current batter. When
        // a baseball game starts, the visiting team bats first,
        // while the home team pitches/fields first.
        this.currentPitcher = homeTeam.getFirstPitcher();
        this.currentBatter = awayTeam.getFirstBatter();

        // Construct base path
        this.basePath = new BasePath();
    }

    public int getInning() {
        return inning;
    }

    public boolean getIsTopOfInning() {
        return topOfInning;
    }

    public void advanceInning() {
        if (this.inning == 9 && !this.topOfInning) {
            this.isPlaying = false;
        } else if (this.getIsTopOfInning()){
            topOfInning = !topOfInning;
        } else {
            topOfInning = !topOfInning;
            inning += 1;
        }
    }

    public int getOuts() {
        return outs;
    }

    public void advanceOuts() {
        this.outs += 1;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    // Returns the current player as an instance of a CurrentPlayer.
    public CurrentPitcher getCurrentPitcher() {
        Player pitcher = currentPitcher;
        return new CurrentPitcher(pitcher, 0);
    }

    public void setCurrentPitcher(Player currentPitcher) {
        this.currentPitcher = currentPitcher;
    }

    public CurrentBatter getCurrentBatter() {
        Player batter = currentBatter;
        CurrentTeam associatedCurrentTeam = new CurrentTeam(batter.getTeam());
        return new CurrentBatter(batter, batter.getBattingAverage(), associatedCurrentTeam);
    }

    public void setCurrentBatter(Player currentBatter) {
        this.currentBatter = currentBatter;
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }
    public void endGame() {
        this.isPlaying = false;
    }

    public void resetOuts() {
        this.outs = 0;
    }

    public void putOnBase(int baseNumber, Player player) {
        if (baseNumber == 1) {
            basePath.firstBase.addPlayer(player);
        } else if (baseNumber == 2) {
            basePath.secondBase.addPlayer(player);
        } else if (baseNumber == 3) {
            basePath.thirdBase.addPlayer(player);
        }
    }
    public void incrementScore(int increment) {
        this.homeTeamRuns += increment;
    }

    public int getHomeTeamRuns() {
        return this.homeTeamRuns;
    }

    public int getAwayTeamRuns() {
        return this.awayTeamRuns;
    }

    private BasePath getBasePath() {
        return this.basePath;
    }
    public void walkBatter() {
        LinkedList<BasePath.Base> basePath = getBasePath().getBasePathLinkedList();
        for (int i = 0; i < 3; i++) {
            BasePath.Base base = basePath.get(i);
            if (base.isOccupied()) {
//                for
            }
        }
    }


}
