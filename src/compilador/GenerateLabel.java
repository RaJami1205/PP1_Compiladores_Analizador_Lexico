package Compilador;

/**
 * Clase encargada de generar etiquetas (labels) únicas utilizadas durante
 * la generación de código intermedio.
 * 
 * Esta clase mantiene un contador interno que se incrementa cada vez que
 * se solicita una nueva etiqueta. Las etiquetas generadas siguen el formato
 * "L1", "L2", "L3"
 */
public class GenerateLabel {
    private static int count = 0;

    public static String newLabel() {
        return "L" + (++count);
    }

    public static void reset() {
        count = 0;
    }
}