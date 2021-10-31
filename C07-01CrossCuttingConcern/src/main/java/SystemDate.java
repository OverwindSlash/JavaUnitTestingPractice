import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDate {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
    private static Date date;

    public SystemDate() {
    }

    public static Date getDate() {
        if (date != null) {
            return date;
        }
        else {
            return new Date();
        }
    }

    public static void setDate(Date value) {
        date = value;
    }

    public static void setDate(String dateStr) throws ParseException {
        date = df.parse(dateStr);
    }

    public static void resetDate() {
        date = null;
    }
}
