import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MockitoNewSampleTests {
    // 18. 抽象类的 Spy 和 Mock
    @Test
    public void spyOrMockOnAbstractClass() {
        //convenience API, new overloaded spy() method:
        SomeAbstract spy = spy(SomeAbstract.class);
        when(spy.returnIntMethod()).thenReturn(10);

        assertEquals("one", spy.returnStringMethod());
        assertEquals(10, spy.returnIntMethod());

        //Robust API, via settings builder:
        SomeAbstract mock1 = mock(SomeAbstract.class, withSettings()
                .useConstructor().defaultAnswer(CALLS_REAL_METHODS));
        when(mock1.returnIntMethod()).thenReturn(100);
        when(mock1.returnStringMethod()).thenReturn("two");

        assertEquals("two", mock1.returnStringMethod());
        assertEquals(100, mock1.returnIntMethod());

        //Mocking an abstract class with constructor arguments (only available since 2.7.14)
        OtherAbstract mock2 = mock(OtherAbstract.class, withSettings()
                .useConstructor("arg1", 123).defaultAnswer(CALLS_REAL_METHODS));
        when(mock2.doSomething()).thenReturn("arg1_123");

        assertEquals("arg1", mock2.foo());
        assertEquals("arg1_123", mock2.doSomething());

        //Mocking a non-static inner abstract class:
        InnerAbstract mock3 = mock(InnerAbstract.class, withSettings()
                .useConstructor().outerInstance(this).defaultAnswer(CALLS_REAL_METHODS));
        doNothing().when(mock3).bar();

        mock3.bar();

        verify(mock3).bar();
    }

    public abstract class InnerAbstract {
        public abstract void bar();
    }

    // 19. 深度 Mock
    // RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象，方法deepstubsTest和deepstubsTest2是等价的
    @Test
    public void deepstubsTest(){
        Account account=mock(Account.class,RETURNS_DEEP_STUBS);

        when(account.getRailwayTicket().getDestination()).thenReturn("Beijing");
        account.getRailwayTicket().getDestination();

        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }

    @Test
    public void deepstubsTest2(){
        Account account=mock(Account.class);
        RailwayTicket railwayTicket=mock(RailwayTicket.class);
        when(account.getRailwayTicket()).thenReturn(railwayTicket);
        when(railwayTicket.getDestination()).thenReturn("Beijing");

        account.getRailwayTicket().getDestination();
        verify(account.getRailwayTicket()).getDestination();
        assertEquals("Beijing",account.getRailwayTicket().getDestination());
    }

    public class RailwayTicket{
        private String destination;

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }

    public class Account{
        private RailwayTicket railwayTicket;

        public RailwayTicket getRailwayTicket() {
            return railwayTicket;
        }

        public void setRailwayTicket(RailwayTicket railwayTicket) {
            this.railwayTicket = railwayTicket;
        }
    }

    // 20. Mock final 方法或类
    @Test
    public void mockFinalMethodOrClass() {
        //Mocking class with final function.
        FinalFunctionClass mock1 = mock(FinalFunctionClass.class);
        when(mock1.finalMethod()).thenReturn(1);
        assertEquals(1, mock1.finalMethod());

        //Mocking final class.
        FinalClass mock2 = mock(FinalClass.class);
        when(mock2.normalMethod()).thenReturn(2);
        assertEquals(2, mock2.normalMethod());
    }

    // 21. Mock 静态方法
    // mockStatic 返回一个 MockedStatic 对象, 它是一个有作用域的 Mock 对象
    @Test
    public void mockStaticMethod() {
        assertEquals("Mockito", StaticUtils.name());

        //Mocking static method in scope.
        try (MockedStatic<StaticUtils> utilities = mockStatic(StaticUtils.class)) {
            utilities.when(StaticUtils::name).thenReturn("Test");
            assertEquals("Test", StaticUtils.name());
        }

        assertEquals("Mockito", StaticUtils.name());
    }

    @Test
    public void mockStaticMethodWithParams() {
        assertTrue(StaticUtils.range(2, 6).contains(5));

        //Mocking static method with parameters in scope.
        try (MockedStatic<StaticUtils> utilities = mockStatic(StaticUtils.class)) {
            utilities.when(() -> StaticUtils.range(2, 6))
                    .thenReturn(Arrays.asList(10, 11, 12));

            assertTrue(StaticUtils.range(2, 6).contains(12));
        }

        assertTrue(StaticUtils.range(2, 6).contains(5));
    }

    // 22. Mock 构造函数
    // 构造函数 Mock 可以让代码中既有的 new 操作，返回 Mock 对象
    @Test
    public void mockConstructor() {
        assertEquals("foo", new Foo().method());

        //In this scope, new created Foo object will be mocked.
        try (MockedConstruction mocked = mockConstruction(Foo.class)) {
            Foo foo = new Foo();
            when(foo.method()).thenReturn("bar");
            assertEquals("bar", foo.method());
        }

        assertEquals("foo", new Foo().method());
    }
}
