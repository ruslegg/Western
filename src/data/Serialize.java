package data;

import Model.TeamLeader;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Serialize {
    public static ArrayList<User> userList =  new ArrayList();
    public static ArrayList<TeamLeader> teamLeadersList=new ArrayList<>();

    public static void serialize(Object o) throws IOException {
        if (o instanceof User){
//            Writer wr = new FileWriter("src/data/users/user.txt",true);
//            BufferedWriter bw = new BufferedWriter(wr);
//            bw.write(((User) o).getName());
//            bw.write(" ");
//            bw.write(((User) o).getClassNumber());
//            bw.write(" ");
//            bw.write(((User) o).getTeam());
//            bw.newLine();
//            bw.close();

        }
        if(o instanceof TeamLeader){
//            Writer wr=new FileWriter("src/data/users/teamleader.txt", true);
//            BufferedWriter bw=new BufferedWriter(wr);
//            bw.write(((TeamLeader) o).getName());
//            bw.write(" ");
//            bw.write(((TeamLeader) o).getClassNumber());
//            bw.write(" ");
//            bw.write(((TeamLeader) o).getTeam());
//            bw.write(" ");
//            bw.write(String.valueOf(((TeamLeader) o).isTeamLeader()));
//
//            bw.newLine();
//            bw.close();
        }
    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        Scanner userScanner = new Scanner(new File("src/data/users/user.txt"));
        while(userScanner.hasNext()) {
            String firstName = userScanner.next();
            String lastName = userScanner.next();
            String classNumber = userScanner.next();
            String team = userScanner.next();
            User user = new User(firstName + " " + lastName, classNumber, team);
            userList.add(user);
            System.out.println(user);
        }

        Scanner teamLeaderScanner= new Scanner(new File("src/data/users/teamLeader.txt"));
        while(teamLeaderScanner.hasNext()){
            String firstName= teamLeaderScanner.next();
            String lastName= teamLeaderScanner.next();
            String classNumber=teamLeaderScanner.next();
            String team=teamLeaderScanner.next();
            Boolean isTeamLeader=Boolean.valueOf(teamLeaderScanner.next());
            TeamLeader teamLeader= new TeamLeader(firstName+" " + " "+ lastName, classNumber, team, isTeamLeader);
            teamLeadersList.add(teamLeader);
            System.out.println(teamLeader);
        }




    }
}
