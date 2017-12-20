package Model.TableView;

/**
 * Object that stands for specific Subject's Leader board TableView between students
 */
public class UserGameSubjectLeaderBoard {
    private int studentId, studentScore;
    private String studentName;

    public UserGameSubjectLeaderBoard(int studentId, int studentScore, String studentName) {
        this.studentId = studentId;
        this.studentScore = studentScore;
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

}
