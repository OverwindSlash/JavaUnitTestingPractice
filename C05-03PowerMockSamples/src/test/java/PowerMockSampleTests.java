import com.sample.powermock.CollaboratorForPartialMocking;
import com.sample.powermock.CollaboratorWithFinalMethods;
import com.sample.powermock.CollaboratorWithStaticMethods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.sample.powermock.*")
public class PowerMockSampleTests {
    // 1. Mock 构造函数和 final 函数
    @Test
    public void mockConstructorsAndFinalMethods() throws Exception {
        CollaboratorWithFinalMethods mock = PowerMockito.mock(CollaboratorWithFinalMethods.class);

        //Whenever the no-arg constructor of that class is invoked,
        //a mock instance should be returned rather than a real one
        PowerMockito.whenNew(CollaboratorWithFinalMethods.class).withNoArguments().thenReturn(mock);

        // Verify mock construction after a normal new process
        CollaboratorWithFinalMethods collaborator = new CollaboratorWithFinalMethods();
        PowerMockito.verifyNew(CollaboratorWithFinalMethods.class).withNoArguments();

        //An expectation is set to the final method helloMethod()
        PowerMockito.when(collaborator.helloMethod()).thenReturn("Hello PowerMock!");

        String welcome = collaborator.helloMethod();

        //Use Mockito.verify
        Mockito.verify(collaborator).helloMethod();
        assertEquals("Hello PowerMock!", welcome);
    }

    // 2. Mock 静态函数
    @Test
    public void mockStaticMethod() {
        PowerMockito.mockStatic(CollaboratorWithStaticMethods.class);

        //Stubbing static methods.
        PowerMockito.when(CollaboratorWithStaticMethods.firstMethod(Mockito.anyString()))
                .thenReturn("Hello PowerMock!");
        PowerMockito.when(CollaboratorWithStaticMethods.secondMethod())
                .thenReturn("Nothing special");
        PowerMockito.when(CollaboratorWithStaticMethods.thirdMethod())
                .thenThrow(RuntimeException.class);

        //Call and assert stubbed static methods.
        assertEquals("Hello PowerMock!", CollaboratorWithStaticMethods.firstMethod("Whoever"));
        assertEquals("Hello PowerMock!", CollaboratorWithStaticMethods.firstMethod("Whatever"));
        try {
            CollaboratorWithStaticMethods.thirdMethod();
        }
        catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }

        //Verify method call times.
        PowerMockito.verifyStatic(CollaboratorWithStaticMethods.class, Mockito.times(2));
        CollaboratorWithStaticMethods.firstMethod(Mockito.anyString());

        PowerMockito.verifyStatic(CollaboratorWithStaticMethods.class, Mockito.never());
        CollaboratorWithStaticMethods.secondMethod();
    }

    // 3. 部分Mock
    @Test
    public void partialMock() throws Exception {
        //Stubbing static method
        PowerMockito.spy(CollaboratorForPartialMocking.class);
        PowerMockito.when(CollaboratorForPartialMocking.staticMethod()).thenReturn("I am a static mock method.");
        assertEquals("I am a static mock method.", CollaboratorForPartialMocking.staticMethod());
        PowerMockito.verifyStatic(CollaboratorForPartialMocking.class);
        CollaboratorForPartialMocking.staticMethod();

        //Stubbing final method
        CollaboratorForPartialMocking collaborator = new CollaboratorForPartialMocking();
        CollaboratorForPartialMocking mock = PowerMockito.spy(collaborator);
        PowerMockito.when(mock.finalMethod()).thenReturn("I am a final mock method.");
        assertEquals("I am a final mock method.", mock.finalMethod());
        Mockito.verify(mock).finalMethod();     // Use Mockito.verify

        //Stubbing private method and call real method
        PowerMockito.when(mock, "privateMethod").thenReturn("I am a private mock method.");
        assertEquals("I am a private mock method. Welcome to the Java world.", mock.privateMethodCaller());
        PowerMockito.verifyPrivate(mock).invoke("privateMethod");
    }
}
