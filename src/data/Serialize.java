package data;

import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serialize {
    public static ArrayList<User> userList =  new ArrayList();


    public static void serialize(Object o) throws IOException {
        if (o instanceof User){
            Writer wr = new FileWriter("src/data/users/user.txt",true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(((User) o).getName());
            bw.write(" ");
            bw.write(((User) o).getClassNumber());
            bw.write(" ");
            bw.write(((User) o).getTeam());
            bw.newLine();
            bw.close();

        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(new File("src/data/users/user.txt"));
        while(scanner.hasNext()) {
            String firstName = scanner.next();
            String lastName = scanner.next();
            String classNumber = scanner.next();
            String team = scanner.next();
            User user = new User(firstName + " " + lastName, classNumber, team);
            userList.add(user);
            System.out.println(user);
        }




    }
}
