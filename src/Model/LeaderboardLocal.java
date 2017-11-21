package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.io.*;
import java.util.*;
import java.io.EOFException;

public class LeaderboardLocal implements Serializable {





    //**Read Student objects and add them to list
    public static ObservableList<LeaderboardLocalObject> readStudents(int constestID) {
        ObservableList<LeaderboardLocalObject> leaderboard = new SortedList<LeaderboardLocalObject>(FXCollections.observableArrayList());

        try {
            FileReader reader = new FileReader("src/data/users/results.txt");
            BufferedReader rd = new BufferedReader(reader);
            String s;


            while( (s = rd.readLine()) != null) {
                String[] parts = s.split(" ");

                if()

            }
        }
        catch(IOException e){
            //do nothing
        }


        try {
            while (true) {
                Student student = (Student) input.readObject();
                leaderboard.add(student);
            }
        } catch (EOFException endOfFileException) {
            //System.out.printf("No more records%n");
        } catch (ClassNotFoundException classNotFoundException) {
            //System.err.println("Invalid object type. Terminating.");
        } catch (IOException ioException) {
            //System.err.println("Error reading from file. Terminating.");
        }
        closeFile();

        return leaderboard;
    }







    public static ArrayList<Student> leaderboardSorting(ArrayList<Student> leadeboard) {
        Collections.sort(leadeboard, (o1, o2) -> o1.getScore() - o2.getScore());
        Collections.reverse(leadeboard);


        return leadeboard;
    }



}
