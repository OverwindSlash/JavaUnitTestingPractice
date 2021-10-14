public class FakeLogger implements ILogger {
    private String lastError;

    @Override
    public void logError(String message) {
        lastError = message;
    }

    @Override
    public String getLastError() {
        return lastError;
    }
}
