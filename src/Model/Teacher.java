package Model;

public class Teacher extends User {
    boolean isTeacher;
    Teacher(){

    }
    Teacher(String name,boolean isTeacher){
        this.name=name;
        this.isTeacher=isTeacher;
    }
}
