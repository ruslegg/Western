package Model.TableView;

import Model.SchoolClass;

public class TeacherLineChart {
    SchoolClass schoolClass;
    int number=0;

    public TeacherLineChart(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }
    public void increment(){
        number++;
    }
    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
