package Model.TableView;

public class TeamLeaderBoard {
    String name;
    Integer points;

    public TeamLeaderBoard(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;

    }
    public void addPoints(Integer points){
        this.points+=points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
