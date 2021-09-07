import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockitoBasicTests {
    // 01. 验证行为是否发生
    @Test
    public void verify_behaviour(){
        // 模拟创建一个List对象
        List mock = mock(List.class);

        // 使用mock的对象
        mock.add(1);
        mock.clear();

        // 验证add(1)和clear()行为是否发生
        verify(mock).add(1);
        verify(mock).clear();
    }

    // 02. 模拟所期望的结果
    @Test
    public void when_thenReturn(){
        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);

        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");

        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();

        //验证结果
        assertEquals("hello world world", result);
    }

    // 03. 模拟抛出异常
    @Test(expected = RuntimeException.class)
    public void doThrow_when(){
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        //预设当流关闭时抛出异常
        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    // 04. 使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常
    @Test
    public void returnsSmartNullsTest() {
//        List mock = mock(List.class);
//        System.out.println(mock.get(0));
//        System.out.println(mock.toArray().length);

        List mock = mock(List.class, RETURNS_SMART_NULLS);
        System.out.println(mock.get(0));
        System.out.println(mock.toArray().length);
    }

    // 05. RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象，方法deepstubsTest和deepstubsTest2是等价的
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

    // 05. 模拟参数
    @Test
    public void with_arguments(){
        Comparable comparable = mock(Comparable.class);

        //预设根据不同的参数返回不同的结果
        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));

        //对于没有预设的情况会返回默认值
        assertEquals(0, comparable.compareTo("Not stub"));
    }

    // 06. 匹配任意参数
    @Test
    public void with_unspecified_arguments(){
        List list = mock(List.class);
        //匹配任意参数
        when(list.get(anyInt())).thenReturn(1);
        when(list.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, list.get(1));
        assertEquals(1, list.get(999));
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    private class IsValid implements ArgumentMatcher<Integer> {
        @Override
        public boolean matches(Integer integer) {
            return integer == 1 || integer == 2;
        }
    }

    // 07. 自定义参数匹配
    @Test
    public void argumentMatchersTest(){
        //创建mock对象
        List<String> mock = mock(List.class);

        //argThat(Matches<T> matcher)方法用来应用自定义的规则，可以传入任何实现Matcher接口的实现类。
        when(mock.addAll(argThat(new IsListOfTwoElements()))).thenReturn(true);

        assertTrue(mock.addAll(Arrays.asList("one","two")));
        assertFalse(mock.addAll(Arrays.asList("one","two","three")));
    }

    private class IsListOfTwoElements implements ArgumentMatcher<List> {
        @Override
        public boolean matches(List list) {
            return list.size() == 2;
        }
    }
}
