package Model.TableView;

public class TeamRequest {
    String teamName,teamAbbreviation;

    public TeamRequest(String teamName, String teamAbbreviation) {
        this.teamName = teamName;
        this.teamAbbreviation = teamAbbreviation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }
}
