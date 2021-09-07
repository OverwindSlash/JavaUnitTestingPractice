public class LogAnalyzer {
    private IWebService service;

    public LogAnalyzer(IWebService service) {
        this.service = service;
    }

    public void analyze(String filename) {
        if (filename.length() < 8) {
            service.logError("Filename too short: " + filename);
        }
    }
}
