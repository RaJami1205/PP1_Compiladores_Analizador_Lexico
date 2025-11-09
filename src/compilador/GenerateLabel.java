package Compilador;

public class GenerateLabel {
    private static int count = 0;

    public static String newLabel() {
        return "L" + (++count);
    }

    public static void reset() {
        count = 0;
    }
}