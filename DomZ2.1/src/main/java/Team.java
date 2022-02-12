import model.inter.Action;

public class Team {
    private String teamName;
    private final Action[] teamParticipant;

    public Team(String teamName, Action[] teamParticipant) {

        this.teamName = teamName;
        this.teamParticipant = teamParticipant;
    }
}
