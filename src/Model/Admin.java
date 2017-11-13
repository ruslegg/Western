package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Admin extends User {
    boolean isAdmin;
    Admin(){

    }
    public Admin(String name,boolean isTeacher){
        this.name=name;
        this.isAdmin=isAdmin;
    }

    @Override
    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/users/admin.txt",true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(name);
        bw.write(" ");
        bw.write(String.valueOf(isAdmin));
        bw.newLine();
        bw.close();
    }

    public boolean isTeacher() {
        return isAdmin;
    }

    public void setTeacher(boolean teacher) {
        isAdmin = teacher;
    }


}
