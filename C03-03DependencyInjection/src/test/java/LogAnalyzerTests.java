import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LogAnalyzerTests {
    @Test
    public void isValidFilename_BadExtension_ReturnsFalse() throws Exception {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithbadextension.foo");
        Assert.assertFalse(result);
    }

    @Test
    public void isValidFilename_GoodExtensioLowerCase_ReturnsTrue() throws Exception {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.slf");
        Assert.assertTrue(result);
    }

    @Test
    public void isValidFilename_GoodExtensionUpperCase_ReturnsTrue() throws Exception {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename("filewithgoodextension.SLF");
        Assert.assertTrue(result);
    }

    @Test(expected = NullPointerException.class)
    public void isValidFilename_EmptyFilename_ThrowException() throws Exception {
        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isValidFilename_EmptyFilename_ThrowException_Method2() throws Exception {
        thrown.expect(NullPointerException.class);

        LogAnalyzer logAnalyzer = makeAnalyzer();
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }

    private LogAnalyzer makeAnalyzer() {
        return new LogAnalyzer(new FileExtensionManager());
    }

    @Test
    public void isValidName_NameSupportedExtension_ReturnTrue() throws Exception {
        FakeExtensionManager mgr = new FakeExtensionManager();
        mgr.setWillBeValid(true);   // 配置 Fake 对象

        LogAnalyzer logAnalyzer = new LogAnalyzer(mgr);
        boolean result = logAnalyzer.isValidLogFilename("short.txt");
        Assert.assertTrue(result);
    }

    @Test(expected = Exception.class)
    public void isValidFilename_EmptyFilename_ThrowExceptio_UsingFakeObject() throws Exception {
        FakeExtensionManager mgr = new FakeExtensionManager();
        mgr.setWillThrow(new Exception("Fake exception."));

        LogAnalyzer logAnalyzer = new LogAnalyzer(mgr);
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }
}
