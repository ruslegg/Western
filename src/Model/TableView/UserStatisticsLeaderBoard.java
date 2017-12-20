package Model.TableView;

/**
 * Object that stands for Student's Statistics Leader board TableView between all students
 */
public class UserStatisticsLeaderBoard {
    private String name;
    private Integer points;

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

}
