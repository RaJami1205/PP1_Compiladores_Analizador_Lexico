package Compilador;

import java.util.*;

public class MySymbol {
    // === Atributos principales ===
    private String name;            
    private String type;            
    private String category;        
    private int scopeLevel;        
    private Object value;         
    private String address;         
    private boolean isTemporary;    
    private int offset;             
    private List<String> paramTypes; 
    private String returnType;      
    private boolean isArray;        
    private int arraySize;          


    public MySymbol(String name, String type, String category, Object value, int scopeLevel) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.value = value;
        this.scopeLevel = scopeLevel;
        this.isTemporary = false;
        this.address = name;
        this.paramTypes = new ArrayList<>();
        this.returnType = null;
        this.offset = 0;
        this.isArray = false;
        this.arraySize = -1;
    }

    // === Getters ===
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public Object getValue() { return value; }
    public String getAddress() { return address; }
    public boolean isTemporary() { return isTemporary; }
    public int getScopeLevel() { return scopeLevel; }
    public List<String> getParamTypes() { return paramTypes; }
    public String getReturnType() { return returnType; }
    public boolean isArray() { return isArray; }
    public int getArraySize() { return arraySize; }

    // === Setters ===
    public void setValue(Object value) { this.value = value; }
    public void setType(String type) { this.type = type; }
    public void setAddress(String addr) { this.address = addr; }
    public void setTemporary(boolean temp) { this.isTemporary = temp; }
    public void setOffset(int offset) { this.offset = offset; }
    public void setParamTypes(List<String> params) { this.paramTypes = params; }
    public void setReturnType(String ret) { this.returnType = ret; }
    public void setArray(boolean isArray) { this.isArray = isArray; }
    public void setArraySize(int size) { this.arraySize = size; }

 
    @Override
    public String toString() {
        if (isArray) {
            return String.format(
                "Symbol{name='%s', type='%s', cat='%s', addr='%s', arraySize=%d, temp=%b, value=%s}",
                name, type, category, address, arraySize, isTemporary, value
            );
        } else {
            return String.format(
                "Symbol{name='%s', type='%s', cat='%s', addr='%s', temp=%b, value=%s}",
                name, type, category, address, isTemporary, value
            );
        }
    }
}
