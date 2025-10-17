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
            // Crear tabla de símbolos única
            SymbolTable symbolTable = new SymbolTable();
            
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
            parser.setSymbolTable(symbolTable);
            
            System.out.println("Iniciando análisis sintáctico...");
            parser.parse();
            System.out.println("Errores encontrados: " + parser.errCount);
            
            if (parser.errCount == 0) {
                System.out.println("Análisis completado sin errores. El archivo es válido según la gramática.");
            } else {
                System.out.println("Análisis completado con " + parser.errCount + " errores.");
            }
            
            // Mostrar tabla de símbolos final
            symbolTable.printTable();

        } catch (Exception e) {
            System.out.println("? Error durante el análisis. El archivo no puede ser generado por la gramatica:");
            e.printStackTrace();
        }
    }
}