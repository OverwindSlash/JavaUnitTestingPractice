import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LogAnalyzerTests {
    @Test
    public void analyze_tooShortFilename_callLogger() {
        ILogger logger = mock(ILogger.class);

        LogAnalyzer logAnalyzer = new LogAnalyzer(logger);
        logAnalyzer.analyze("a.txt");

        verify(logger).logError("Filename too short: a.txt");
        //verify(logger).logError("Filename too short: a.txt 111");
    }
}
