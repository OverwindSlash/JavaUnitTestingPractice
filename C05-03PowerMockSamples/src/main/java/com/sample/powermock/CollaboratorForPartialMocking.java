package com.sample.powermock;

public class CollaboratorForPartialMocking {
    public static String staticMethod() {
        return "Hello staticMethod!";
    }

    public final String finalMethod() {
        return "Hello finalMethod!";
    }

    private String privateMethod() {
        return "Hello privateMethod!";
    }

    public String privateMethodCaller() {
        return privateMethod() + " Welcome to the Java world.";
    }
}