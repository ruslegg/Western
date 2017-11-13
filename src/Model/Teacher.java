package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Teacher extends User {
    boolean isTeacher;
    Teacher(){

    }
    public Teacher(String name,boolean isTeacher){
        this.name=name;
        this.isTeacher=isTeacher;
    }

    @Override
    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/users/teacher.txt",true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(name);
        bw.write(" ");
        bw.write(String.valueOf(isTeacher));
        bw.newLine();
        bw.close();
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }


}
