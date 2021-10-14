import org.junit.Assert;
import org.junit.Test;

public class LogAnalyzerTests {
    @Test
    public void analyze_tooShortFilename_callLogger() {
        ILogger logger = new FakeLogger();

        LogAnalyzer logAnalyzer = new LogAnalyzer(logger);
        logAnalyzer.analyze("a.txt");

        Assert.assertEquals("Filename too short: a.txt", logger.getLastError());
    }
}
