import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Categories;
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
    public void isValidFilename_EmptyFilename_ThrowException() {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isValidFilename_EmptyFilename_ThrowException_Method2() {
        LogAnalyzer logAnalyzer = makeAnalyzer();

        thrown.expect(NullPointerException.class);
        System.out.println("befor exception");
        boolean result = logAnalyzer.isValidLogFilename(null);
        System.out.println("after exception");
    }

    @Ignore
    @Test
    public void isValidFilename_EmptyFilename_ThrowException_Ignored() {
        Assert.assertFalse(true);
    }

    private LogAnalyzer makeAnalyzer() {
        return new LogAnalyzer();
    }
}
