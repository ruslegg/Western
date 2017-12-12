package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class Teacher extends User implements Serializable {

    public Teacher() {

    }

    public Teacher(Integer id, String name, String username, String password) {
        super(id, name, username, password);
    }

    public void serializeRequest() throws IOException, ClassNotFoundException {
        LoginController.requestsList.add(this);
        File file = new File("src/data/requests/teachers.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.requestsList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.requestsList = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
    }

    public void serialize() throws IOException {
        LoginController.teacherList.add(this);

        File file = new File("src/data/users/teachers.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.teacherList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.teacherList = (ArrayList) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }

    public void deleteRequest() throws IOException, ClassNotFoundException {
        for (int i=0;i< LoginController.requestsList.size();i++){
            if (LoginController.requestsList.get(i).getName().equals(this.name)){
                LoginController.requestsList.remove(i);
            }
        }
        File file = new File("src/data/requests/teachers.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.requestsList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        LoginController.requestsList = (ArrayList) in.readObject();
        in.close();
        fileIn.close();
    }


}
