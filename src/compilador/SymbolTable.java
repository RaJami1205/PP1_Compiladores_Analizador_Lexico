package Compilador;

import java.util.*;

public class SymbolTable {

    private static class Symbol {
        String name;
        String type;
        String category; // variable, function, etc.
        int scope;
        Object value;

        Symbol(String name, String type, String category, int scope, Object value) {
            this.name = name;
            this.type = type;
            this.category = category;
            this.scope = scope;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[name=%s, type=%s, category=%s, scope=%d, value=%s]",
                    name, type, category, scope, value);
        }
    }

    private final List<Symbol> symbols = new ArrayList<>();
    private int currentScope = 0;
    private final Deque<Integer> scopeStack = new ArrayDeque<>();

    public SymbolTable() {
        scopeStack.push(0); // ámbito global
    }

    /** Entra en un nuevo ámbito */
    public void enterScope() {
        currentScope++;
        scopeStack.push(currentScope);
    }

    /** Sale del ámbito actual */
    public void exitScope() {
        int scopeToRemove = scopeStack.pop();
        symbols.removeIf(sym -> sym.scope == scopeToRemove);
    }

    /** Agrega un símbolo (variable, función, etc.) */
    public boolean addSymbol(String name, String type, String category, Object value) {
        int scope = scopeStack.peek();
        for (Symbol sym : symbols) {
            if (sym.name.equals(name) && sym.scope == scope) {
                return false; // ya existe en este ámbito
            }
        }
        symbols.add(new Symbol(name, type, category, scope, value));
        return true;
    }

    /** Busca un símbolo en el ámbito actual o superiores */
    public Symbol lookup(String name) {
        for (int i = symbols.size() - 1; i >= 0; i--) {
            Symbol sym = symbols.get(i);
            if (sym.name.equals(name)) return sym;
        }
        return null;
    }

    /** Imprime la tabla */
    public void printTable() {
        System.out.println("=== TABLA DE SÍMBOLOS ===");
        Map<Integer, List<Symbol>> grouped = new LinkedHashMap<>();
        for (Symbol s : symbols) {
            grouped.computeIfAbsent(s.scope, k -> new ArrayList<>()).add(s);
        }
        for (Map.Entry<Integer, List<Symbol>> e : grouped.entrySet()) {
            System.out.println("Ámbito " + e.getKey() + ":");
            for (Symbol s : e.getValue()) {
                System.out.println("  " + s);
            }
        }
    }
}
