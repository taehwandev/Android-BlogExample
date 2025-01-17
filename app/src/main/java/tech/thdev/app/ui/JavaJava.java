package tech.thdev.app.ui;

public class JavaJava {

    private static JavaJava INSTANCE = new JavaJava();

    public static final String value = "avalue";

    private JavaJava() {
        System.out.println("init");
    }

    public static String getValue() {
        return value;
    }

    public static JavaJava getInstance() {
        return INSTANCE;
    }

    static {
        System.out.println("static");
    }
}
