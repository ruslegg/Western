package Model.TableView;

/**
 * Object used for Teacher Requests in Admin's Menu for TableView
 */
public class AdminRequest {

    private String teacherRequestName;

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
