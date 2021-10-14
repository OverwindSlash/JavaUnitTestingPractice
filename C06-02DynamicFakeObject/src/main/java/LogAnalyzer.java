public class LogAnalyzer {
    private ILogger logger;

    public LogAnalyzer(ILogger logger) {
        this.logger = logger;
    }

    public void analyze(String filename) {
        if (filename.length() < 8) {
            logger.logError("Filename too short: " + filename);
        }
    }
}
