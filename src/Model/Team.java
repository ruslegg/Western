package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

/**
 * This object stands for the teams for the students
 */
public class Team implements Serializable {
    private String name, abbreviation;

    /**
     * Creates a new Team Object
     * @param name - constructor's value
     * @param abbreviation constructor's value
     */
    public Team(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public Team() {
    }

    /**
     * Serialized to a file and retrieving the information from the file in order to avoid conflicts
     * @throws IOException caused by serialization
     * @throws ClassNotFoundException caused by serialization
     */
    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.getTeamList().add(this);
        File file = new File("src/data/attributes/teams.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getTeamList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setTeamList((ArrayList) in.readObject());
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
}
