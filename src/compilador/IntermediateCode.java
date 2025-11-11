package Compilador;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class IntermediateCode {
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