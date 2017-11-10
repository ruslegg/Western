package Model;

import data.Serialize;

import java.io.*;

public class User implements Serializable {
    String name;
    String team;
    String classNumber;

    public User() {

    }

    public User(String name, String team, String classNumber) {
        this.name = name;
        this.team = team;
        this.classNumber = classNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
}
