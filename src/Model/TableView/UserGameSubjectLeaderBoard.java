package Model.TableView;

public class UserGameSubjectLeaderBoard {
    private int studentId,studentScore;
    private String studentName;

    public UserGameSubjectLeaderBoard(int studentId, int studentScore, String studentName) {
        this.studentId = studentId;
        this.studentScore = studentScore;
        this.studentName = studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(int studentScore) {
        this.studentScore = studentScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
