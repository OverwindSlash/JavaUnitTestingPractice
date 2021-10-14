import org.junit.Assert;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LogAnalyzerTests {
    @Test
    public void analyze_TooShortFilename_CallWebService() throws Exception {
        IWebService stubWebService = mock(IWebService.class);
        doThrow(new Exception("Fake exception")).when(stubWebService).logError(anyString());

        IEmailService mockEmail = mock(IEmailService.class);

        LogAnalyzer logAnalyzer = new LogAnalyzer(stubWebService, mockEmail);
        String tooShortFilename = "abc.txt";
        logAnalyzer.analyze(tooShortFilename);

        // 对 Mock 对象进行断言，而不是 LogAnalyzer
        verify(mockEmail).sendMail("admin@test.com", "fake exception", "can't log");
    }
}
