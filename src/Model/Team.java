package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class Team implements Serializable {
    String name,abbreviation;

    public Team(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Team() {
    }

    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.teamList.add(this);
        File file = new File("src/data/attributes/teams.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.teamList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.teamList=(ArrayList) in.readObject();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
