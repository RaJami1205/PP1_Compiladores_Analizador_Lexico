package Compilador;
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

    public void printCode() {
        System.out.println("=== CÓDIGO INTERMEDIO (CUÁDRUPLOS) ===");
        for (int i = 0; i < code.size(); i++) {
            System.out.println(i + ": " + code.get(i));
        }
    }
}