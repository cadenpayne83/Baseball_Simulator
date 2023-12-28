import java.util.LinkedList;

public class GameState {
    private int inning;

    private boolean topOfInning;
    private int outs;
    private CurrentPitcher currentPitcher;
    private CurrentBatter currentBatter;
    private CurrentTeam homeTeam;
    private int homeTeamRuns;
    private CurrentTeam awayTeam;
    private int awayTeamRuns;
    private boolean isPlaying;
    private BasePath basePath;

    public GameState(int inning, int outs, CurrentTeam homeTeam, CurrentTeam awayTeam, CurrentPitcher currentPitcher, CurrentBatter currentBatter) {
        this.inning = inning;
        this.outs = 0;
        this.homeTeam = homeTeam;
        this.homeTeamRuns = 0;
        this.awayTeam = awayTeam;
        this.awayTeamRuns = 0;
        this.isPlaying = true;
        this.topOfInning = true;

        // Construct teams in order to grab players.
        this.homeTeam.constructLineUp();
        this.awayTeam.constructLineUp();

        // Grab current pitcher and current batter. When
        // a baseball game starts, the visiting team bats first,
        // while the home team pitches/fields first.
        this.currentPitcher = homeTeam.getFirstPitcher();
        this.currentBatter = awayTeam.getFirstBatter();

        // Construct base path
        this.basePath = new BasePath(this);
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
        if (this.outs == 1) {
            System.out.println("1 out!");
        } else {
            System.out.println(outs + " outs!");
        }
    }

    public CurrentTeam getHomeTeam() {
        return homeTeam;
    }

    public CurrentTeam getAwayTeam() {
        return awayTeam;
    }

    // Returns the current player as an instance of a CurrentPlayer.
    public CurrentPitcher getCurrentPitcher() {
        return currentPitcher;
    }

    public void setCurrentPitcher(CurrentPitcher currentPitcher) {
        this.currentPitcher = currentPitcher;
    }

    public CurrentBatter getCurrentBatter() {
        return currentBatter;
    }

    public void setCurrentBatter(CurrentBatter currentBatter) {
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

    public void walk(CurrentBatter batter) {
        basePath.walk(batter);
    }

    public void single(CurrentBatter batter) {
        basePath.single(batter);
    }

    public void double_(CurrentBatter batter) {
        basePath.double_(batter);
    }

    public void triple(CurrentBatter batter) {
        basePath.triple(batter);
    }

    public void homeRun() {
        basePath.homeRun();
    }

    public void incrementScore(int increment) {
        if (this.getIsTopOfInning()) {
            this.awayTeamRuns += increment;
        } else {
            this.homeTeamRuns += increment;
        }

        System.out.println("Score is away team " + awayTeamRuns + ", home team " + homeTeamRuns + ".");

    }

    public int getHomeTeamRuns() {
        return this.homeTeamRuns;
    }

    public int getAwayTeamRuns() {
        return this.awayTeamRuns;
    }

    public BasePath getBasePath() {
        return this.basePath;
    }

    public int numberOfRunnersOnBase() {
        return this.basePath.getBasePathLinkedList().size();//doesn't work, always 3
    }
}
