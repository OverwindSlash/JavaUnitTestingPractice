import org.junit.After;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class TestBase {
    @Before
    public void setUp() throws Exception {
        ILogger logger = mock(ILogger.class);
        LoggingFacility.setLogger(logger);
    }

    @After
    public void tearDown() throws Exception {
        LoggingFacility.setLogger(null);
    }
}
