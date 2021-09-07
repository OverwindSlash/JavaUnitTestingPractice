import org.junit.Assert;
import org.junit.Test;

public class LogAnalyzerTests {
    @Test
    public void isValidFilename_BadExtension_ReturnsFalse() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithbadextension.foo");
        Assert.assertFalse(result);
    }

    @Test
    public void isValidFilename_GoodExtensioLowerCase_ReturnsTrue() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.slf");
        Assert.assertTrue(result);
    }

    @Test
    public void isValidFilename_GoodExtensionUpperCase_ReturnsTrue() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.SLF");
        Assert.assertTrue(result);
    }
}
