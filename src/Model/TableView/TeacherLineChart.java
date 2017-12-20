package Model.TableView;

import Model.SchoolClass;

/**
 * Object standing for Teacher Line Chart
 */
public class TeacherLineChart {
    private SchoolClass schoolClass;
    private int number = 0;

    public TeacherLineChart(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    /**
     * Increment number of student in that class
     */
    public void increment() {
        number++;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
