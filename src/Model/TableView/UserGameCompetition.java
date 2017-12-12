package Model.TableView;

public class UserGameCompetition {
    String subject;
    int id,teacherId;

    public UserGameCompetition(String subject, int id, int teacherId) {
        this.subject = subject;
        this.id = id;
        this.teacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
