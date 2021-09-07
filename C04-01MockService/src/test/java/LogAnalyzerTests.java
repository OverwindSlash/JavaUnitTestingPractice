import org.junit.Assert;
import org.junit.Test;

public class LogAnalyzerTests {
    @Test
    public void analyze_TooShortFilename_CallWebService() {
        FakeWebService mockService = new FakeWebService();

        LogAnalyzer logAnalyzer = new LogAnalyzer(mockService);

        String tooShortFilename = "abc.txt";

        logAnalyzer.analyze(tooShortFilename);

        // 对 Mock 对象进行断言，而不是 LogAnalyzer
        Assert.assertTrue(mockService.getLastError().contains("Filename too short: abc.txt"));
    }
}
