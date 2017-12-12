package Model.TableView;

import sun.util.calendar.LocalGregorianCalendar;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserGameContest {
    String contestSubject,toDate,daysLeft;
    int id,teacherId;

    public UserGameContest(int id,String contestSubject, String toDate, int teacherId) {
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

    public String countDaysLeft(String toDateString) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Date toDate = dateFormatter.parse(toDateString);
        Date currentDate = new Date();
        long toDateLong = toDate.getTime();
        long currentLong = currentDate.getTime();
        long days = (toDateLong-currentLong)/(1000*60*60*24);
        return String.valueOf(days+1);
    }

    public String getContestSubject() {
        return contestSubject;
    }

    public void setContestSubject(String contestSubject) {
        this.contestSubject = contestSubject;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        this.daysLeft = daysLeft;
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

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
