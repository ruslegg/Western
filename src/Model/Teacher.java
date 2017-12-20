package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

/**
 * Teacher object that extends User
 */
public class Teacher extends User implements Serializable {

    public Teacher() {
    }

    public Teacher(Integer id, String name, String username, String password) {
        super(id, name, username, password);
    }

    /**
     * Serialize request to admin
     * @throws IOException - caused by serialization
     * @throws ClassNotFoundException - caused by serialization
     */
    public void serializeRequest() throws IOException, ClassNotFoundException {
        LoginController.getRequestsList().add(this);
        File file = new File("src/data/requests/teachers.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getRequestsList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setRequestsList((ArrayList) in.readObject());
        in.close();
        fileIn.close();
    }

    /**
     * delete teacher request
     * @throws IOException - caused by serialization
     * @throws ClassNotFoundException - caused by serialization
     */
    public void deleteRequest() throws IOException, ClassNotFoundException {
        for (int i = 0; i < LoginController.getResultsList().size(); i++) {
            if (LoginController.getRequestsList().get(i).getName().equals(this.getName())) {
                LoginController.getRequestsList().remove(i);
            }
        }
        File file = new File("src/data/requests/teachers.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getRequestsList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.setRequestsList((ArrayList) in.readObject());
        in.close();
        fileIn.close();
    }

    /**
     * Serialize teacher to TeacherList
     * @throws IOException caused by serialization
     */
    public void serialize() throws IOException {
        LoginController.getTeacherList().add(this);
        File file = new File("src/data/users/teachers.ser");
        FileOutputStream fileOut = new FileOutputStream(file, false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.getTeacherList());
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.setTeacherList((ArrayList) in.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }


}
