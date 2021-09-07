public class LogAnalyzer {
    private IExtensionManager mgr;

    public LogAnalyzer()
    {
        this.setMgr(new FileExtensionManager());
    }

    public boolean isValidLogFilename(String filename) throws Exception {
        return getMgr().isValid(filename);
    }

    // 给 LogAnalyzer 暴露了新接口，后续可以用 工厂 改善
    public IExtensionManager getMgr() {
        return mgr;
    }

    // 给 LogAnalyzer 暴露了新接口，后续可以用 工厂 改善
    public void setMgr(IExtensionManager mgr) {
        this.mgr = mgr;
    }
}
