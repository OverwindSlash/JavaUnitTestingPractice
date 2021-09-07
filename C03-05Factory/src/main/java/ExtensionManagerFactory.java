public class ExtensionManagerFactory {
    private static IExtensionManager extensionManager = null;

    public static IExtensionManager create() {
        if (extensionManager != null) {
            return extensionManager;
        }

        return new FileExtensionManager();
    }

    public static void setExtensionManager(IExtensionManager manager) {
        extensionManager = manager;
    }
}
