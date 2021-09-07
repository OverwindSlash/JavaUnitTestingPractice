public class LogAnalyzer {
    private IExtensionManager mgr;

    public LogAnalyzer(IExtensionManager mgr) {
        this.mgr = mgr;
    }

    public boolean isValidLogFilename(String filename) throws Exception {
        return mgr.isValid(filename);
    }
}
