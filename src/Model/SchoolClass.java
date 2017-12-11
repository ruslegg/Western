package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class SchoolClass implements Serializable {
    private int number;
    private String letter;

    public SchoolClass(int number, String letter) throws IOException, ClassNotFoundException {
        this.number = number;
        this.letter = letter;
        serialize();
    }

    public SchoolClass() {
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

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void serialize() throws IOException, ClassNotFoundException {
        LoginController.classList.add(this);
        File file = new File("src/data/attributes/classes.ser");
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.classList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.classList=(ArrayList) in.readObject();
    }

}

