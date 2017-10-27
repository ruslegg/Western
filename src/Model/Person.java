package Model;

public class Person {
    public String firstName,lastName,score,field;
    public int rank,time;



    public Person() {

    }
    public Person(String firstName,String lastName){
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public Person(int rank,String firstName, String lastName, String score, String field, int time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
        this.time = time;
        this.field = field;
        this.rank = rank;
    }
}
