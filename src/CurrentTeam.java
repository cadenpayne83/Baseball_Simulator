public class CurrentTeam {

    private Team associatedTeam;

    public CurrentTeam(Team team) {
        this.associatedTeam = team;
    }

    public Team getAssociatedTeam() {
        return this.associatedTeam;
    }
}
