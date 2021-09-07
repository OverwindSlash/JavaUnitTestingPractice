public class AlwaysValidFakeExtensionManager implements IExtensionManager {
    @Override
    public boolean isValid(String filename) {
        return true;
    }
}
