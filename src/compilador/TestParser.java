package Compilador;

import java.io.FileReader;
import java_cup.runtime.Symbol;
import ParserLexer.Lexer;
import ParserLexer.Parser;
import ParserLexer.sym;

public class TestParser {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java Compilador.TestParser <archivo>");
            return;
        }

        String archivo = args[0];
        System.out.println("? Analizando archivo: " + archivo);
        System.out.println("-------------------------------------");

        try {
            
            FileReader fr = new FileReader(archivo);
            Lexer lexer = new Lexer(fr);
            
            Symbol token;
            System.out.println("Tokens detectados por el lexer:");
            while ((token = lexer.next_token()).sym != sym.EOF) {
                System.out.println("Token: " + token.sym + " -> " + lexer.yytext());
            }
            System.out.println("Fin de tokens\n");

            // Reiniciamos el lector para el parser
            fr = new FileReader(archivo);
            lexer = new Lexer(fr);
            
            // Pasar la tabla de símbolos al parser
            Parser parser = new Parser(lexer);
            
            System.out.println("Iniciando análisis sintáctico...");
            parser.parse();
            System.out.println("-------------------------------------");

            System.out.println("\n--- Resumen de errores ---");
            if (Parser.errores.isEmpty()) {
                System.out.println("No se encontraron errores sintácticos.");
                System.out.println("El archivo es valido y puede ser generado por la gramatica.\n");
            } else {
                for (String err : Parser.errores) {
                    System.out.println(err);
                }
                System.out.println("\nEl archivo no puede ser generado por la gramatica.\n");
            }
            

        } catch (Exception e) {
            System.out.println("? Error durante el análisis. El archivo no puede ser generado por la gramatica:");
            e.printStackTrace();
        }
    }
}