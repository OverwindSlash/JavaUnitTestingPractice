public class FakeWebService implements IWebService {
    private Exception exception;
    private String lastError;

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void logError(String message) throws Exception {
        if (exception != null) {
            throw exception;
        }
        lastError = message;
    }

    @Override
    public String getLastError() {
        return lastError;
    }
}
