import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LogAnalyzerTests {

    private LogAnalyzer logAnalyzer;

    @Before
    public void setUp() throws Exception {
        logAnalyzer = new LogAnalyzer();
    }

    @Test
    public void isValidFilename_BadExtension_ReturnsFalse() {
        //LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithbadextension.foo");
        Assert.assertFalse(result);
    }

    @Test
    public void isValidFilename_GoodExtensioLowerCase_ReturnsTrue() {
        //LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.slf");
        Assert.assertTrue(result);
    }

    @Test
    public void isValidFilename_GoodExtensionUpperCase_ReturnsTrue() {
        //LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.SLF");
        Assert.assertTrue(result);
    }

    @After
    public void tearDown() throws Exception {
        logAnalyzer = null;     // 仅演示用，正式场景中非必需
    }
}
