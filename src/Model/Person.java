package Model;

public class Person {
    public String name,score,field;
    public int rank,time;
    public Person() {

    }
    public Person(int rank,String name, String score, String field, int time) {
        this.score = score;
        this.time = time;
        this.field = field;
        this.rank = rank;
        this.name= name;
    }
}
