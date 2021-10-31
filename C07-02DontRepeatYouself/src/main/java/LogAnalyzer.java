public class LogAnalyzer {
    public void analyze(String filename) {
        if (filename.length() < 8) {
            LoggingFacility.log("Filename too short: " + filename);
        }
    }
}
