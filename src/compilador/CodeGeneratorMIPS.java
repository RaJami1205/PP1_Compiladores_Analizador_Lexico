package Compilador;

import java.io.FileWriter;
import java.io.IOException;

public class CodeGeneratorMIPS {
    private StringBuilder code;
    private int tempCount = 0;

    public CodeGeneratorMIPS() {
        code = new StringBuilder();
        generarEncabezado();
    }

    private void generarEncabezado() {
        code.append(".data\n");
        code.append("result: .word 0\n");
        code.append(".text\n");
        code.append(".globl main\n");
        code.append("main:\n");
    }

    public String newTemp() {
        return "$t" + (tempCount++ % 10);
    }

    public void addInstruction(String instr) {
        code.append("\t").append(instr).append("\n");
    }

    public void generarFin() {
        code.append("\n\tli $v0, 10\n\tsyscall\n");
    }

    public void guardarArchivo(String nombreArchivo) throws IOException {
        generarFin();
        FileWriter writer = new FileWriter(nombreArchivo);
        writer.write(code.toString());
        writer.close();
    }

    public String getCode() {
        return code.toString();
    }
}
