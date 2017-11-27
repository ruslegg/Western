package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Teacher extends User {

    public Teacher() {

    }

    public Teacher(String id, String name, String username, String password) {
        super(id, name, username, password);
    }

    @Override
    public void serialize() throws IOException {
        Writer wr = new FileWriter("src/data/requests/teachers.txt",true);
        BufferedWriter bw = new BufferedWriter(wr);
        bw.write(id);
        bw.write(" ");
        bw.write(name);
        bw.write(" ");
        bw.write(username);
        bw.write(" ");
        bw.write(password);
        bw.write(" ");

        bw.newLine();
        bw.close();
    }

}
