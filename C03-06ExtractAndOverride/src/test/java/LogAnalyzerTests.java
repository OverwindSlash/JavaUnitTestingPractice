import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class LogAnalyzerTests {
    @Test
    public void overrideTest() throws Exception {
        FakeExtensionManager stub = new FakeExtensionManager();
        stub.setWillBeValid(true);

        TestableLogAnalyzer logan = new TestableLogAnalyzer(stub);

        boolean result = logan.isValidLogFilename("file.ext");
        assertTrue(result);
    }
}
