public class LogAnalyzerUsingFactoryMethod {
    public boolean isValidLogFilename(String filename) throws Exception {
        return getManager().isValid(filename);
    }

    protected IExtensionManager getManager() {
        return new FileExtensionManager();
    }
}
