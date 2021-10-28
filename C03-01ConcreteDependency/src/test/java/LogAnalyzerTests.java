import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LogAnalyzerTests {
    @Test
    public void isValidFilename_BadExtension_ReturnsFalse() {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithbadextension.foo");
        Assert.assertFalse(result);
    }

    @Test
    public void isValidFilename_GoodExtensioLowerCase_ReturnsTrue() {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.slf");
        Assert.assertTrue(result);
    }

    @Test
    public void isValidFilename_GoodExtensionUpperCase_ReturnsTrue() {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.SLF");
        Assert.assertTrue(result);
    }

    @Test(expected = NullPointerException.class)
    public void isValidFilename_EmptyFilename_ThrowException_Method1() {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isValidFilename_EmptyFilename_ThrowException_Method2() {
        thrown.expect(NullPointerException.class);

        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }

    private LogAnalyzer makeAnalyzer() {
        return new LogAnalyzer();
    }
}
