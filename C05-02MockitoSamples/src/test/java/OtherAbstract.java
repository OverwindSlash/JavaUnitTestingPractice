public abstract class OtherAbstract {
    private final String param1;
    private final int param2;

    public OtherAbstract(String param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public String foo() {
        return param1;
    }

    public abstract String doSomething();
}
