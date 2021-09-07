import org.junit.Test;
import org.mockito.*;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockitoSampleTests {

    // 01. 验证行为
    // Mock 将记住所有调用，可以有选择地验证感兴趣的任何行为
    // 实际工作中，请不要直接 Mock List类，请改用真实实例
    @Test
    public void verifyBehaviour() {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    // 02. 简单Stubbing
    // 未被 Stubbing 的函数会返回默认值
    // Stubbing 可以被覆盖，所以顺序很重要（并不推荐，影响可读性）
    // Stubbing 的函数不管被调用多少次都会返回被 stubbing 的值
    @Test(expected = RuntimeException.class)
    public void doSimpleStubbing() {
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenReturn("second");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //followings both return "first"
        assertEquals("first", mockedList.get(0));
        assertEquals("first", mockedList.get(0));

        //following throws runtime exception, not "second"
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        assertEquals(null, mockedList.get(999));
    }
    
    // 03. 参数匹配器 (Argument Matchers)
    // Mockito 缺省使用 equals() 方法匹配参数；当需要额外的灵活性时，可以使用参数匹配器
    @Test
    public void useSimpleArgumentMatchers() {
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");
        assertEquals("element", mockedList.get(999));

        //stubbing using custom matcher (isValid() returns matcher implementation):
        when(mockedList.contains(argThat(isValid()))).thenReturn(true);
        assertTrue(mockedList.contains("aaa"));
        assertFalse(mockedList.contains("aa"));

        //you can also verify using an argument matcher or Java 8 Lambdas
        verify(mockedList).get(anyInt());
        verify(mockedList).contains(argThat(str -> str.toString().length() == 3));
    }

    private ArgumentMatcher<String> isValid() {
        return str -> str.length() >= 3;
    }

    // 04. 验证调用次数
    @Test
    public void verifyInvocationCount() {
        LinkedList mockedList = mock(LinkedList.class);

        //using mock
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //following two verifications work exactly the same - times(1) is used by default
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        //exact number of invocations verification
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //verification using never(). never() is an alias to times(0)
        verify(mockedList, never()).add("never happened");

        //verification using atLeast()/atMost()
        verify(mockedList, atMostOnce()).add("once");
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    // 05. Stubbing void 函数抛异常
    @Test(expected = RuntimeException.class)
    public void stubbingVoidFunctionThrowException() {
        LinkedList mockedList = mock(LinkedList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();

        //following throws RuntimeException:
        mockedList.clear();
    }

    // 06. 验证执行顺序
    // 不必一一验证所有交互，而只需按顺序验证那些您感兴趣的交互
    @Test
    public void verifyInOrder() {
        // A. Single mock whose methods must be invoked in a particular order
        List singleMock = mock(List.class);

        //using a single mock
        singleMock.add("was added first");
        singleMock.add("was added second");

        //create an inOrder verifier for a single mock
        InOrder inOrderSingle = inOrder(singleMock);

        //following will make sure that add is first called with "was added first", then with "was added second"
        inOrderSingle.verify(singleMock).add("was added first");
        inOrderSingle.verify(singleMock).add("was added second");

        // B. Multiple mocks that must be used in a particular order
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        //using mocks
        firstMock.add("was called first");
        secondMock.add("was called second");

        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrderMulti = inOrder(firstMock, secondMock);

        //following will make sure that firstMock was called before secondMock
        inOrderMulti.verify(firstMock).add("was called first");
        inOrderMulti.verify(secondMock).add("was called second");
    }

    // 07. 验证调用未发生
    @Test
    public void verifyNotHappen() {
        ArrayList mockOne = mock(ArrayList.class);
        ArrayList mockTwo = mock(ArrayList.class);
        ArrayList mockThree = mock(ArrayList.class);

        //using mocks - only mockOne is interacted
        mockOne.add("one");
        verify(mockOne).add("one");

        //verify that method was never called on a mock
        verify(mockOne, never()).add("two");

        //verify that other mocks were not interacted
        verifyNoMoreInteractions(mockTwo, mockThree);
    }

    // 08. 验证冗余调用
    // 不建议在每个测试方法中使用 verifyNoMoreInteractions()，仅在需要时使用
    @Test(expected = NoInteractionsWanted.class)
    public void verifyRedundantCall() {
        ArrayList mockedList = mock(ArrayList.class);
        mockedList.add("one");
        mockedList.add("two");

        verify(mockedList).add("one");

        //following verification will fail
        //throw NoInteractionsWanted
        verifyNoMoreInteractions(mockedList);
    }

    // 10. Stubbing 连续调用
    // 有时需要为相同的方法调用Stubbing不同的返回值/异常，典型的用例是模拟迭代器
    @Test
    public void stubbingConsecutiveCalls () {
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(1))
                .thenThrow(new RuntimeException())
                .thenReturn("foo");

        //First call: throws runtime exception:
        try {
            mockedList.get(1);
            fail();
        }
        catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }

        //Second call: returns "foo"
        assertEquals("foo", mockedList.get(1));
        //Any consecutive call: returns "foo" as well (last stubbing wins).
        assertEquals("foo", mockedList.get(1));
    }

    // 11. 使用回调进行 Stubbing
    // 不推荐使用
    // 建议简单地使用 thenReturn() 或 thenThrow() ，这应该足以测试/测试驱动任何干净和简单的代码
    @Test
    public void stubbingWithCallback() {
        MockClass mock = mock(MockClass.class);
        when(mock.someMethod(anyString())).thenAnswer(
                new Answer() {
                    public Object answer(InvocationOnMock invocation) {
                        Object[] args = invocation.getArguments();
                        Object mock = invocation.getMock();
                        return "called with arguments: " + Arrays.toString(args);
                    }
                });

        assertEquals("called with arguments: [foo]", mock.someMethod("foo"));
    }

    private class MockClass {
        public String someMethod(String param) {
            throw  new UnsupportedOperationException();
        }
    }

    // 12. doXXX 方法家族，用途如下：
    // stub void 方法
    // stub spy 对象上的方法
    // 多次 stub 相同的方法，以在测试过程中更改模拟的行为
    @Test(expected = RuntimeException.class)
    public void testDoWhen() {
        ArrayList mockedList = mock(ArrayList.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    // 13. Spy 真实对象
    // 当使用 Spy 时将调用真正的方法（除非方法被Stub）
    @Test
    public void spyRealObject() {
        List list = new LinkedList();
        List spy = spy(list);

        //optionally, you can stub out some methods:
        when(spy.size()).thenReturn(100);

        //using the spy calls *real* methods
        spy.add("one");
        spy.add("two");

        //return "one" - the first element of a list
        assertEquals("one", spy.get(0));

        //size() method was stubbed - 100 is printed
        assertEquals(100, spy.size());

        //optionally, you can verify
        verify(spy).add("one");
        verify(spy).add("two");
    }

    // 有时，将 when(Object) 用于 stubbing spy是不可能或不切实际的。
    // 因此，当使用 spy 时，需要考虑 doReturn|Answer|Throw() 系列方法用于 stubbing
    @Test
    public void stubSpy() {
        List list = new LinkedList();
        List spy = spy(list);

        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        try {
            when(spy.get(0)).thenReturn("foo");
        }
        catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }

        //You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
    }

    // 14. 更改未 stub 调用的缺省返回值
    @Test
    public void returnsSmartNulls() {
        // use RETURNS_DEFAULTS as default setting
        MockClass mockOne = mock(MockClass.class);

        // null is default return value of un-stubbed function.
        try {
            String result = mockOne.someMethod("1");
            int length = result.length();
            fail();
        }
        catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }

        // use RETURNS_SMART_NULLS
        MockClass mockTwo = mock(MockClass.class, RETURNS_SMART_NULLS);

        // change default return value of un-stubbed function to "".
        String result = mockTwo.someMethod("1");
        int length = result.length();
    }
    
    // 15. 捕获参数进行进一步断言
    // 建议只在 verify 中使用 ArgumentCaptor
    @Test
    public void capturingArguments() {
        PersonService personService = mock(PersonService.class);
        Person person = new Person(1, "john");
        personService.Update(person);

        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        verify(personService).Update(argument.capture());

        assertEquals(1, argument.getValue().getId());
        assertEquals("john", argument.getValue().getName());
    }

    private class Person {
        private final int id;
        private final String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    private class PersonService {
        public void Update(Person person) {
            throw new UnsupportedOperationException();
        }
    }

    // 16. 部分 Mock
    // 可使用 Spy 和 thenCallRealMethod() 进行部分 Mock
    @Test
    public void partialMock() {
        //you can create partial mock with spy() method:
        List list = spy(new LinkedList());

        //you can enable partial mock capabilities selectively on mocks:
        MockClass mock = mock(MockClass.class);
        //Be sure the real implementation is 'safe'.
        //If real implementation throws exceptions or depends on specific state of the object then you're in trouble.
        when(mock.someMethod("1")).thenCallRealMethod();
    }

    // 17. 重置 Mock
    // 测试方法中间的 reset() 是代码的坏味道，代表你同时在做不止一件事
    @Test
    public void resetMock() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        assertEquals(10,list.size());

        // reset mock
        reset(list);
        assertEquals(0,list.size());
    }
}
