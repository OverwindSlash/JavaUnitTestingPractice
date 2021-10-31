import org.junit.Test;

public class NewLogAnalyzerTests extends TestBase {
    @Test
    public void analyze_emptyFile_throwExcption() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.analyze("emptyfile.txt");
        // 测试其余部分
    }
}
