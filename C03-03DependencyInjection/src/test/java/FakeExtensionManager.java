public class FakeExtensionManager implements IExtensionManager {
    private boolean willBeValid = false;
    private Exception willThrow = null;

    @Override
    public boolean isValid(String filename) throws Exception {
        if (willThrow != null) {
            throw willThrow;
        }
        return willBeValid;
    }

    public void setWillBeValid(boolean willBeValid) {
        this.willBeValid = willBeValid;
    }

    public void setWillThrow(Exception willThrow) {
        this.willThrow = willThrow;
    }
}
