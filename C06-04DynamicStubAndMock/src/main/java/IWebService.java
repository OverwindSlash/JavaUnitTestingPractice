public interface IWebService {
    void logError(String message) throws Exception;
    String getLastError();
}
