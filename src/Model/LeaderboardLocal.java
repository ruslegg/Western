package Model;

import java.io.*;
import java.util.*;
import java.io.EOFException;

public class LeaderboardLocal implements Serializable {




    //**Opens Students.txt
    public static void openFile() {
        try {
            input = new ObjectInputStream(Files.newInputStream(Paths.get("src/data/users/student.txt")));
        } catch (IOException ioException) {
            // System.err.println("Error opening file.");
        }
    }

    //**Read Student objects and add them to ArrayList
    public static ArrayList<Student> readStudents() {
        ArrayList<Student> leaderboard = new ArrayList<Student>();
        openFile();

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
