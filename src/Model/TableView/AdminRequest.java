package Model.TableView;

public class AdminRequest {

    String teacherRequestName;

    public AdminRequest(String teacherRequestName) {
        this.teacherRequestName = teacherRequestName;
    }

    public String getTeacherRequestName() {
        return teacherRequestName;
    }

    public void setTeacherRequestName(String teacherRequestName) {
        this.teacherRequestName = teacherRequestName;
    }
}
