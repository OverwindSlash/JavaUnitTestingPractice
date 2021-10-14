import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LogAnalyzerTests {
    @Test
    public void isValidName_NameSupportedExtension_ReturnTrue() throws Exception {
        IExtensionManager mgr = mock(IExtensionManager.class);
        when(mgr.isValid("short.txt")).thenReturn(true);

        LogAnalyzer logAnalyzer = new LogAnalyzer(mgr);
        boolean result = logAnalyzer.isValidLogFilename("short.txt");
        Assert.assertTrue(result);
    }

    @Test
    public void isValidName_NameSupportedExtension_ReturnFalse() throws Exception {
        IExtensionManager mgr = mock(IExtensionManager.class);
        when(mgr.isValid("short.txt")).thenReturn(true);

        LogAnalyzer logAnalyzer = new LogAnalyzer(mgr);
        boolean result = logAnalyzer.isValidLogFilename("long.txt");
        Assert.assertFalse(result);
    }

    @Test
    public void isValidName_AnyExtension_ReturnTrue() throws Exception {
        IExtensionManager mgr = mock(IExtensionManager.class);
        when(mgr.isValid(anyString())).thenReturn(true);

        LogAnalyzer logAnalyzer = new LogAnalyzer(mgr);
        boolean result = logAnalyzer.isValidLogFilename("any.csv");
        Assert.assertTrue(result);
    }

    @Test(expected = Exception.class)
    public void isValidFilename_EmptyFilename_ThrowExceptio_UsingFakeObject() throws Exception {
        IExtensionManager mgr = mock(IExtensionManager.class);
        doThrow(new Exception("Fake exception.")).when(mgr.isValid(null));

        LogAnalyzer logAnalyzer = new LogAnalyzer(mgr);
        boolean result = logAnalyzer.isValidLogFilename(null);
        Assert.assertFalse(result);
    }
}
