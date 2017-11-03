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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
