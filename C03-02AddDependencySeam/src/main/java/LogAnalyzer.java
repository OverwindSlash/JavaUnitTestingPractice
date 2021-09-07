public class LogAnalyzer {
    public boolean isValidLogFilename(String filename) {
        /*if (filename.toLowerCase(Locale.ROOT).endsWith(".slf")) {
            return true;
        }
        return false;*/

        // 需要通过依赖注入插入不同的Stub
        IExtensionManager mgr = new FileExtensionManager();
        //IExtensionManager mgr = new AlwaysValidFakeExtensionManager();

        return mgr.isValid(filename);
    }
}
