package Model;

import Controllers.LoginController;

import java.io.*;
import java.util.ArrayList;

public class Admin extends User implements Serializable {

        public Admin(Integer id, String name, String username, String password) {
        super(id, name, username, password);
    }

    @Override
    public void serialize() throws IOException {
        LoginController.adminList.add(this);
        File file = new File("src/data/users/admins.ser");
        FileOutputStream fileOut = new FileOutputStream(file,false);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(LoginController.adminList);
        out.close();
        fileOut.close();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            LoginController.adminList = (ArrayList) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        in.close();
        fileIn.close();
    }
}