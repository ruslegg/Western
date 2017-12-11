package Model.TableView;

public class UserStatisticsLeaderBoard {
    String name;
    Integer points;

    public UserStatisticsLeaderBoard(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
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
