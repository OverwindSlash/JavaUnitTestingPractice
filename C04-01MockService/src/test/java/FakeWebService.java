public class FakeWebService implements IWebService {
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
