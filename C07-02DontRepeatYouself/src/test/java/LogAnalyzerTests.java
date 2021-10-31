import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class LogAnalyzerTests {
    @Before
    public void setUp() throws Exception {
        ILogger logger = mock(ILogger.class);
        LoggingFacility.setLogger(logger);
    }

    @Test
    public void analyze_emptyFile_throwExcption() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyze("emptyfile.txt");
        // 测试其余部分
    }

    @After
    public void tearDown() throws Exception {
        // 测试之间需要重置静态资源
        LoggingFacility.setLogger(null);
    }
}
