public class GameState {
    private int inning;
    private int outs;
    private Player currentPitcher;
    private Player currentBatter;
    private Team homeTeam;
    private int homeTeamRuns;
    private Team awayTeam;
    private int awayTeamRuns;
    private boolean isPlaying;

    public GameState(int inning, Team homeTeam, Team awayTeam, Player currentPitcher, Player currentBatter) {
        this.inning = inning;
        this.outs = 0;
        this.currentPitcher = currentPitcher;
        this.currentBatter = currentBatter;
        this.homeTeam = homeTeam;
        this.homeTeamRuns = 0;
        this.awayTeam = awayTeam;
        this.awayTeamRuns = 0;
        this.isPlaying = true;
    }

    public int getInning() {
        return inning;
    }

    public void advanceInning() {
        if (this.inning == 9) {
            this.isPlaying = false;
        } else {
            this.inning += 1;
        }
    }

    public int getOuts() {
        return outs;
    }

    public void advanceOuts() {
        this.outs += 1;
        if (this.outs == 3) {
            advanceInning();
        }
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Player getCurrentPitcher() {
        return currentPitcher;
    }

    public void setCurrentPitcher(Player currentPitcher) {
        this.currentPitcher = currentPitcher;
    }

    public Player getCurrentBatter() {
        return currentBatter;
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
}
