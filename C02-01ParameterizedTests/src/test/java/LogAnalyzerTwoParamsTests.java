import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LogAnalyzerTwoParamsTests {
    private String filename;
    private boolean expected;

    @Test
    public void isValidFilename_BadExtension_ReturnsFalse() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithbadextension.foo");
        Assert.assertFalse(result);
    }

    /*@Test
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
    }*/

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Object[][] {
                {"filewithgoodextension.slf", true},
                {"filewithgoodextension.SLF", true},
                {"filewithgoodextension.foo", false}});
    }

    public LogAnalyzerTwoParamsTests(String filename, boolean expected) {
        this.filename = filename;
        this.expected = expected;
    }

    @Test
    public void isValidLogFilename_ValidExtensions_ReturnTrue() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename(filename);
        Assert.assertEquals(expected, result);
    }
}
