import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLogger {
    public static String createMessage(String info) {
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(now) + " " + info;
    }

    public static String createMessageCrossCuttingConcern(String info) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(SystemDate.getDate()) + " " + info;
    }
}
