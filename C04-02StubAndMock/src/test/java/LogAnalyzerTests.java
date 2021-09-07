import org.junit.Assert;
import org.junit.Test;

public class LogAnalyzerTests {
    @Test
    public void analyze_TooShortFilename_CallWebService() {
        FakeWebService stubWebService = new FakeWebService();
        stubWebService.setException(new Exception("Fake exception"));

        FakeEmailService mockEmail = new FakeEmailService();

        LogAnalyzer logAnalyzer = new LogAnalyzer(stubWebService, mockEmail);
        String tooShortFilename = "abc.txt";
        logAnalyzer.analyze(tooShortFilename);

        // 对 Mock 对象进行断言，而不是 LogAnalyzer
        Assert.assertTrue(mockEmail.getTo().contains("admin@test.com"));
        Assert.assertTrue(mockEmail.getSubject().contains("fake exception"));
        Assert.assertTrue(mockEmail.getBody().contains("can't log"));
    }
}
