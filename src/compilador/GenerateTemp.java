package Compilador;
/**
 * Clase encargada de generar variables temporales utilizadas durante
 * la generación de código intermedio del compilador.
 * 
 * Estas variables se crean de forma secuencial y única para almacenar
 * resultados intermedios de expresiones aritméticas, lógicas 
 * 
 *"t1", "t2", "t3"
 */
public class GenerateTemp {
    private static int count = 0;

    public static String newTemp() {
        return "t" + (++count);
    }

    public static void reset() {
        count = 0;
    }
}