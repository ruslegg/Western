package Model.TableView;

/**
 * Object standing for Team Leader-board TableView
 */
public class TeamLeaderBoard {
    private String name;
    private Integer points;

    public TeamLeaderBoard(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adding points to the object
     * @param points - adding parameter value to object's current points
     */
    public void addPoints(Integer points) {
        this.points += points;
    }

}
