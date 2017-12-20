package Model.TableView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Object that stands for Contest TableView in Student's Games
 */
public class UserGameContest {
    private String contestSubject, toDate, daysLeft;
    private int id, teacherId;

    public UserGameContest(int id, String contestSubject, String toDate, int teacherId) {
        this.contestSubject = contestSubject;
        this.toDate = toDate;
        this.id = id;
        this.teacherId = teacherId;
        try {
            this.daysLeft = countDaysLeft(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculcates days left for the contest
     *
     * @param toDateString - date until contest is available
     * @return days left
     * @throws ParseException caused by date formatter
     */
    public String countDaysLeft(String toDateString) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date toDate = dateFormatter.parse(toDateString);
        Date currentDate = new Date();
        long toDateLong = toDate.getTime();
        long currentLong = currentDate.getTime();
        long days = (toDateLong - currentLong) / (1000 * 60 * 60 * 24);
        return String.valueOf(days + 1);
    }

    public String getContestSubject() {
        return contestSubject;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

}
