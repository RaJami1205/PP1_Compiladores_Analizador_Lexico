package Compilador;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Clase encargada de gestionar y almacenar el código intermedio del compilador.
 * El código intermedio se representa en forma de cuádruplas:
 * (operador, argumento1, argumento2, resultado)
 * La clase permite agregar cuádruplas, generar temporales y etiquetas,
 * imprimir el código intermedio en consola o exportarlo a un archivo de texto.

 */
public class IntermediateCode {
    /** Lista que almacena todas las cuádruplas */
    private List<Quadruple> code = new ArrayList<>();

    public void add(String op, String arg1, String arg2, String res) {
        code.add(new Quadruple(op, arg1, arg2, res));
    }

    public String newTemp() {
        return GenerateTemp.newTemp();
    }

    public String newLabel() {
        return GenerateLabel.newLabel();
    }
      /**
     * Exporta el código intermedio generado a un archivo de texto.
     * 
     * El archivo incluirá un encabezado y una lista numerada de todas las cuádruplas.
     * En caso de error, se mostrará un mensaje en la consola.
     * 
     * @param filePath Ruta completa del archivo donde se guardará el código intermedio.
     */
    public void exportToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("=== CÓDIGO INTERMEDIO (CUÁDRUPLOS) ===");
            int index = 0;
            for (Quadruple q : code) {
                writer.println(index + ": " + q.toString());
                index++;
            }
        } catch (IOException e) {
            System.err.println("[ERROR] No se pudo exportar el código intermedio: " + e.getMessage());
        }
    }

    public void printCode() {
        System.out.println("=== CÓDIGO INTERMEDIO (CUÁDRUPLOS) ===");
        for (int i = 0; i < code.size(); i++) {
            System.out.println(i + ": " + code.get(i));
        }
    }
}