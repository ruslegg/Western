package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.io.*;
import java.util.*;
import java.io.EOFException;

public class LeaderboardLocal implements Serializable {





    //**Reads Student objects returns a descending list regarding their scores
    public static ObservableList<LeaderboardLocalObject> readStudents(int constestID) {
        ObservableList<LeaderboardLocalObject> leaderboard = new SortedList<LeaderboardLocalObject>(FXCollections.observableArrayList());

        try {
            FileReader reader = new FileReader("src/data/attributes/results.txt");
            BufferedReader rd = new BufferedReader(reader);
            String s;

            while( (s = rd.readLine()) != null) {
                String[] parts = s.split(" ");

                if(constestID == Integer.parseInt(parts[5])) {
                    LeaderboardLocalObject local = new LeaderboardLocalObject(parts[3], parts[1] + " " + parts[2], Integer.parseInt(parts[6]));
                    leaderboard.add(local);
                }
            }

            leaderboardSorting(leaderboard);

            for(int i=0; i< leaderboard.size(); i++)
            {leaderboard.get(i).setRank(i+1);}
        }
        catch(IOException e){
            //do nothing
        }
        return leaderboard;
}

    public static void leaderboardSorting(ObservableList<LeaderboardLocalObject> leadeboard) {
        Collections.sort(leadeboard, (o1, o2) -> o1.getScore() - o2.getScore());
        Collections.reverse(leadeboard);
    }

}
