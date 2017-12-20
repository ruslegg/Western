package Model.TableView;

/**
 * Object standing for Student's Team Join TableView
 */
public class TeamRequest {
    private String teamName, teamAbbreviation;

    public TeamRequest(String teamName, String teamAbbreviation) {
        this.teamName = teamName;
        this.teamAbbreviation = teamAbbreviation;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

}
