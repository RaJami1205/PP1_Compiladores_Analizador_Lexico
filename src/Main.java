import java.io.*;
import java_cup.runtime.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("JAVA MAIN");
            return;
        }
        
        try {
            // Leer archivo fuente
            FileReader reader = new FileReader(args[0]);
            
            // Crear lexer
            Lexer lexer = new Lexer(reader);
            
            // Crear parser
            Parser parser = new Parser(lexer);
            

            
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + args[0]);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}