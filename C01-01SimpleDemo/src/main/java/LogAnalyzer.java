import java.util.Locale;

public class LogAnalyzer {
    public boolean isValidLogFilename(String filename) {
        if (filename.toLowerCase(Locale.ROOT).endsWith(".slf")) {
            return true;
        }
        return false;
    }
}
