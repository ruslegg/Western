package Model;

public class LeaderboardLocalObject {

    private int rank;
    private String abreviation;
    private String name;
    private int score;

    public LeaderboardLocalObject(String abreviation, String name, int score) {

        this.abreviation = abreviation;
        this.name = name;
        this.score = score;
    }

    public LeaderboardLocalObject() {
        this("", "", 0);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        score = score;
    }
}
