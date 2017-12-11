package Model.TableView;

import sun.util.calendar.LocalGregorianCalendar;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UserGameContest {
    String contestSubject, fromDate,toDate,daysLeft;

    public UserGameContest(String contestSubject, String fromDate, String toDate) throws ParseException {
        this.contestSubject = contestSubject;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.daysLeft=countDaysLeft(toDate);
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
}
