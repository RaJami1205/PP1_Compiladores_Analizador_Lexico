package Compilador;


/**
 * Clase que representa una instrucción en forma de cuádrupla dentro del
 * código intermedio del compilador.
 * 
 * En la generación de código intermedio, las operaciones se expresan mediante
 * cuádruplas del tipo:
 * 
 *      (operador, argumento1, argumento2, resultado)

 */
public class Quadruple {
    private String operator;
    private String arg1;
    private String arg2;
    private String result;

    public Quadruple(String operator, String arg1, String arg2, String result) {
        this.operator = operator;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
    }

    public String getOperator() { return operator; }
    public String getArg1() { return arg1; }
    public String getArg2() { return arg2; }
    public String getResult() { return result; }

    @Override
    public String toString() {
        return "(" + operator + ", " + arg1 + ", " + arg2 + ", " + result + ")";
    }
}