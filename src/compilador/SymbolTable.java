package Compilador;

import java.util.*;

public class SymbolTable {
    private List<Map<String, Symbol>> scopes;
    private int currentScope;

    public SymbolTable() {
        scopes = new ArrayList<>();
        enterScope(); // ámbito global
    }

    public void enterScope() {
        scopes.add(new HashMap<>());
        currentScope = scopes.size() - 1;
    }

    public void exitScope() {
        if (!scopes.isEmpty()) {
            //scopes.remove(scopes.size() - 1);
            //currentScope = scopes.size() - 1;
        }
    }

    public boolean addSymbol(String name, String type, String category, Object value) {
        Map<String, Symbol> scope = scopes.get(currentScope);
        if (scope.containsKey(name)) {
            return false;
        }
        Symbol sym = new Symbol(name, type, category, value, currentScope);
        scope.put(name, sym);
        return true;
    }

    public Symbol getSymbol(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Map<String, Symbol> scope = scopes.get(i);
            if (scope.containsKey(name))
                return scope.get(name);
        }
        return null;
    }

    public boolean updateValue(String name, Object value) {
        Symbol s = getSymbol(name);
        if (s != null) {
            s.setValue(value);
            return true;
        }
        return false;
    }

    private int tempCount = 0;

    public Symbol newTemp(String type) {
        String tempName = "t" + tempCount++;
        Symbol temp = new Symbol(tempName, type, "temporal", null, currentScope);
        temp.setTemporary(true);
        addSymbol(tempName, type, "temporal", null);
        return temp;
    }

    public void setFunctionInfo(String name, List<String> paramTypes, String returnType) {
        Symbol func = getSymbol(name);
        if (func != null) {
            func.setParamTypes(paramTypes);
            func.setReturnType(returnType);
        }
    }

    public void printTable() {
        System.out.println("========= TABLA DE SÍMBOLOS =========");
        for (int i = 0; i < scopes.size(); i++) {
            System.out.println("Ámbito nivel " + i + ":");
            for (Symbol s : scopes.get(i).values()) {
                System.out.println("  " + s);
            }
        }
        System.out.println("=====================================");
    }
}