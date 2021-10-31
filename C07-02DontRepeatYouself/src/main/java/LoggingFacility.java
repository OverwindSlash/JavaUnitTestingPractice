public class LoggingFacility {
    private static ILogger logger;

    public static void log(String text) {
        getLogger().logError(text);
    }

    public static ILogger getLogger() {
        return logger;
    }

    public static void setLogger(ILogger logger) {
        LoggingFacility.logger = logger;
    }
}
