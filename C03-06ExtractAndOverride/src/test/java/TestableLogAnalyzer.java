public class TestableLogAnalyzer extends LogAnalyzerUsingFactoryMethod{
    private IExtensionManager manager;

    public TestableLogAnalyzer(IExtensionManager manager) {
        this.manager = manager;
    }

    @Override
    protected IExtensionManager getManager() {
        return manager;
    }
}
