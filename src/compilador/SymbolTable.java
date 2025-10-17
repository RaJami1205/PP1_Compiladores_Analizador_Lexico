package Compilador;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, SymbolInfo> table = new HashMap<>();

    public void add(String name, String type, Object value, int line) {
        table.put(name, new SymbolInfo(name, type, value, line));
    }

    public boolean contains(String name) {
        return table.containsKey(name);
    }

    public SymbolInfo get(String name) {
        return table.get(name);
    }

    // MÉTODO NUEVO PARA ACTUALIZAR VALORES
    public void updateValue(String name, Object value) {
        if (table.containsKey(name)) {
            SymbolInfo info = table.get(name);
            info.value = value;
        }
    }

    public void printTable() {
        System.out.println("Tabla de símbolos:");
        for (SymbolInfo info : table.values()) {
            System.out.println(info);
        }
    }
}

class SymbolInfo {
    String name;
    String type;
    Object value;
    int line;

    public SymbolInfo(String name, String type, Object value, int line) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.line = line;
    }

    public String toString() {
        return String.format("Nombre: %s | Tipo: %s | Valor: %s | Línea: %d", name, type, value, line);
    }
}