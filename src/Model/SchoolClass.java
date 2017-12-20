package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

/**
 * School Class Object that is associated with students
 */
public class SchoolClass implements Serializable {

    private int number;
    private String letter;

    /**
     * Create a new School Class Object
     * @param number - constructor's value
     * @param letter - constructor's value
     */
    public SchoolClass(int number, String letter) {
        this.number = number;
        this.letter = letter;
    }

    public SchoolClass() {
    }

    /**
     * Serializes to a file and retrieving information from the file in order to avoid conflicts
     * @throws IOException caused by serialization
     * @throws ClassNotFoundException caused by serialization
     */
    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.getClassList().add(this);
        File file = new File("src/data/attributes/classes.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getClassList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setClassList((ArrayList) in.readObject());
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLetter() {
        return letter;
    }

}

