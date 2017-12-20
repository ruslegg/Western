package Model;

import java.io.IOException;
import java.io.Serializable;

/**
 * An abstract class that will be used for inheritance to Student, Teacher and Admin Objects
 * This class stands for a basic functionality for an user to play the game
 */
abstract public class User implements Serializable {

    private int id;
    private String name,username,password;

    public User() {
    }

    /**
     * Creates a new user that can be used in the game
     * @param id - Constructor's value
     * @param name - Constructor's value
     * @param username - Constructor's value
     * @param password - Constructor's value
     */
    public User(Integer id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Performs a serialization to a file using the directory written in method
     * @throws IOException caused by serialization
     */
    public abstract void serialize() throws IOException;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
