package Model;

public class Stopwatch
{
    private final long start;
    private double savedTime;

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public void saveTime(double time) {
        double savedTime = time;
    }

    public double showTime(){
        return savedTime;
    }

}
