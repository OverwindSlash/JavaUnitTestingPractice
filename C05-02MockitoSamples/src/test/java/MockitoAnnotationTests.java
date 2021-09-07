import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

// 09. @Mock 注解
public class MockitoAnnotationTests {
    @Mock
    private MockClass mockClass;

    @Test
    public void testObject() {
        // Mock with annotation.
        MockitoAnnotations.initMocks(this);

        mockClass.returnInt();
        verify(mockClass).returnInt();
    }

    private class MockClass {
        public int returnInt() {
            return 0;
        }
    }
}
