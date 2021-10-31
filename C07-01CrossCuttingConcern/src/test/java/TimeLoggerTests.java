import org.junit.After;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class TimeLoggerTests {
    @Test
    public void createMessageCrossCuttingConcern_setManualDate() throws ParseException {
        String dateStr = "2021-10-31 12:13:14";
        SystemDate.setDate(dateStr);

        String message = TimeLogger.createMessageCrossCuttingConcern("test message.");
        assertEquals(dateStr + " test message.", message);
    }

    @After
    public void tearDown() throws Exception {
        SystemDate.resetDate();
    }
}
