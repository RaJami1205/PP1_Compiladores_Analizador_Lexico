package compilador;

import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void GenerarLexerParser() throws Exception {
        String basePath, fullPathLexer, fullPathparser, jlexer, jparser, jlexerCarpeta;
        MainJflexCup mfjc;

        basePath = System.getProperty("user.dir");

        mfjc = new MainJflexCup();

        // Genera el analizdor lexico y sintactico

        fullPathLexer = basePath +"\\app\\src\\SI2025\\basicLexerCupSI2025.jflex";
        fullPathparser = basePath +"\\app\\src\\SI2025\\parserSI2025Ini.cup";
      
        // .java de parser y lexer
        jparser = "parser.java";
        jlexer = "BasicLexerCup.java";
        jlexerCarpeta = "SI2025";
        // elimina los archivos
        Files.deleteIfExists(Paths.get(basePath +"\\app\\src\\main\\java\\ParserLexer\\sym.java"));
        Files.deleteIfExists(Paths.get(basePath + "\\app\\src\\main\\java\\ParserLexer\\" + jparser));
        Files.deleteIfExists(Paths.get(basePath + "\\app\\src\\main\\java\\ParserLexer\\" + jlexer));

        // Crear analizador lexico y sintactico
        // String[] args = {fullPathparser};\\flag para el nombre del parser
        mfjc.iniLexerParser(fullPathLexer, fullPathparser);
        // mover los archivos
        Files.move(
            Paths.get(basePath + "\\sym.java"),
            Paths.get(basePath + "\\app\\src\\main\\java\\ParserLexer\\sym.java")
        );
        Files.move(
            Paths.get(basePath + "\\" + jparser),
            Paths.get(basePath + "\\app\\src\\main\\java\\ParserLexer\\" + jparser)
        );
        Files.move(
            Paths.get(basePath+"\\app\\src\\"+jlexerCarpeta+"\\"+jlexer), 
            Paths.get(basePath+"\\app\\src\\main\\java\\ParserLexer\\"+jlexer)
        );
    }

    public static void PruebasLexerParser() throws Exception {
        String basePath, fullPathScanner, fullPathparser, fullPathParserII2024, fullPathParserV2024;
        MainJflexCup mfjc;

        basePath = System.getProperty("user.dir");

        basePath = System.getProperty("user.dir");
        fullPathScanner = basePath + "\\app\\src\\testing\\ejemplo1.txt";
        fullPathparser = basePath + "\\app\\src\\testing\\ejemploParser.txt";
       

        mfjc = new MainJflexCup();


        mfjc.ejercicioParser(fullPathScanner); 

    }
    

    public String getGreeting() {
        return "Hello, World!";
    }

    public static void main(String[] args) throws Exception {
        //GenerarLexerParser();
        PruebasLexerParser();
    }

}