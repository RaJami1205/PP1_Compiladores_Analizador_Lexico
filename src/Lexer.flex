/* Definiciones para el analizador léxico */
%%
%public
%class Lexer
%unicode
%line
%column
%cup
%cupdebug

%{
    private StringBuilder stringLiteral = new StringBuilder();
    
    private void debug(String token, String lexema) {
        System.out.println("Token: " + token + " | Lexema: " + lexema + 
                         " | Línea: " + (yyline+1) + " | Columna: " + (yycolumn+1));
    }
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

/* Patrones para literales y identificadores */
Letra = [a-zA-Z]
Digito = [0-9]
Espacio = [ \t\r\n]
Signo = [-+]

/* Literales */
Entero = {Signo}?{Digito}+
Flotante = {Signo}?{Digito}+"."{Digito}+
Identificador = {Letra}({Letra}|{Digito}|"_")*

%state STRING
%state CHAR
%state COMMENT_MULTI

%%

/* Palabras reservadas */
<YYINITIAL> "let"           { debug("LET", yytext()); return symbol(sym.LET); }
<YYINITIAL> "function"      { debug("FUNCTION", yytext()); return symbol(sym.FUNCTION); }
<YYINITIAL> "procedure"     { debug("PROCEDURE", yytext()); return symbol(sym.PROCEDURE); }
<YYINITIAL> "principal"     { debug("PRINCIPAL", yytext()); return symbol(sym.PRINCIPAL); }
<YYINITIAL> "int"           { debug("INT", yytext()); return symbol(sym.INT); }
<YYINITIAL> "float"         { debug("FLOAT", yytext()); return symbol(sym.FLOAT); }
<YYINITIAL> "char"          { debug("CHAR", yytext()); return symbol(sym.CHAR); }
<YYINITIAL> "bool"          { debug("BOOL", yytext()); return symbol(sym.BOOL); }
<YYINITIAL> "true"          { debug("TRUE", yytext()); return symbol(sym.TRUE); }
<YYINITIAL> "false"         { debug("FALSE", yytext()); return symbol(sym.FALSE); }
<YYINITIAL> "decide"        { debug("DECIDE", yytext()); return symbol(sym.DECIDE); }
<YYINITIAL> "of"            { debug("OF", yytext()); return symbol(sym.OF); }
<YYINITIAL> "else"          { debug("ELSE", yytext()); return symbol(sym.ELSE); }
<YYINITIAL> "end"           { debug("END", yytext()); return symbol(sym.END); }
<YYINITIAL> "loop"          { debug("LOOP", yytext()); return symbol(sym.LOOP); }
<YYINITIAL> "exit"          { debug("EXIT", yytext()); return symbol(sym.EXIT); }
<YYINITIAL> "when"          { debug("WHEN", yytext()); return symbol(sym.WHEN); }
<YYINITIAL> "for"           { debug("FOR", yytext()); return symbol(sym.FOR); }
<YYINITIAL> "step"          { debug("STEP", yytext()); return symbol(sym.STEP); }
<YYINITIAL> "to"            { debug("TO", yytext()); return symbol(sym.TO); }
<YYINITIAL> "downto"        { debug("DOWNTO", yytext()); return symbol(sym.DOWNTO); }
<YYINITIAL> "do"            { debug("DO", yytext()); return symbol(sym.DO); }
<YYINITIAL> "return"        { debug("RETURN", yytext()); return symbol(sym.RETURN); }
<YYINITIAL> "break"         { debug("BREAK", yytext()); return symbol(sym.BREAK); }
<YYINITIAL> "input"         { debug("INPUT", yytext()); return symbol(sym.INPUT); }
<YYINITIAL> "output"        { debug("OUTPUT", yytext()); return symbol(sym.OUTPUT); }

/* Símbolos especiales */
<YYINITIAL> "¿"             { debug("ABRE_BLOQUE", yytext()); return symbol(sym.ABRE_BLOQUE); }
<YYINITIAL> "?"             { debug("CIERRA_BLOQUE", yytext()); return symbol(sym.CIERRA_BLOQUE); }
<YYINITIAL> "є"             { debug("ABRE_PAR", yytext()); return symbol(sym.ABRE_PAR); }
<YYINITIAL> "э"             { debug("CIERRA_PAR", yytext()); return symbol(sym.CIERRA_PAR); }
<YYINITIAL> "->"            { debug("FLECHA", yytext()); return symbol(sym.FLECHA); }
<YYINITIAL> "++"            { debug("INCREMENTO", yytext()); return symbol(sym.INCREMENTO); }
<YYINITIAL> "--"            { debug("DECREMENTO", yytext()); return symbol(sym.DECREMENTO); }
<YYINITIAL> "@"             { debug("AND", yytext()); return symbol(sym.AND); }
<YYINITIAL> "~"             { debug("OR", yytext()); return symbol(sym.OR); }
<YYINITIAL> "Σ"             { debug("SUMATORIA", yytext()); return symbol(sym.SUMATORIA); }
<YYINITIAL> "//"            { debug("DIV_ENTERA", yytext()); return symbol(sym.DIV_ENTERA); }
<YYINITIAL> "=="            { debug("IGUAL", yytext()); return symbol(sym.IGUAL); }
<YYINITIAL> "!="            { debug("DIFERENTE", yytext()); return symbol(sym.DIFERENTE); }
<YYINITIAL> "<="            { debug("MENOR_IGUAL", yytext()); return symbol(sym.MENOR_IGUAL); }
<YYINITIAL> ">="            { debug("MAYOR_IGUAL", yytext()); return symbol(sym.MAYOR_IGUAL); }

/* Operadores y símbolos simples */
<YYINITIAL> "="             { debug("ASIGNACION", yytext()); return symbol(sym.ASIGNACION); }
<YYINITIAL> "+"             { debug("SUMA", yytext()); return symbol(sym.SUMA); }
<YYINITIAL> "-"             { debug("RESTA", yytext()); return symbol(sym.RESTA); }
<YYINITIAL> "*"             { debug("MULT", yytext()); return symbol(sym.MULT); }
<YYINITIAL> "/"             { debug("DIV", yytext()); return symbol(sym.DIV); }
<YYINITIAL> "%"             { debug("MOD", yytext()); return symbol(sym.MOD); }
<YYINITIAL> "^"             { debug("POTENCIA", yytext()); return symbol(sym.POTENCIA); }
<YYINITIAL> "<"             { debug("MENOR", yytext()); return symbol(sym.MENOR); }
<YYINITIAL> ">"             { debug("MAYOR", yytext()); return symbol(sym.MAYOR); }
<YYINITIAL> "["             { debug("ABRE_CORCHETE", yytext()); return symbol(sym.ABRE_CORCHETE); }
<YYINITIAL> "]"             { debug("CIERRA_CORCHETE", yytext()); return symbol(sym.CIERRA_CORCHETE); }
<YYINITIAL> ":"             { debug("DOS_PUNTOS", yytext()); return symbol(sym.DOS_PUNTOS); }
<YYINITIAL> ","             { debug("COMA", yytext()); return symbol(sym.COMA); }
<YYINITIAL> "$"             { debug("DOLAR", yytext()); return symbol(sym.DOLAR); }

/* Literales numéricos */
<YYINITIAL> {Entero}        { debug("LIT_ENTERO", yytext()); return symbol(sym.LIT_ENTERO, Integer.valueOf(yytext())); }
<YYINITIAL> {Flotante}      { debug("LIT_FLOTANTE", yytext()); return symbol(sym.LIT_FLOTANTE, Float.valueOf(yytext())); }

/* Identificadores */
<YYINITIAL> {Identificador} { debug("ID", yytext()); return symbol(sym.ID, yytext()); }

/* Comentarios de una línea */
<YYINITIAL> "|".*           { debug("COMENTARIO_LINEA", yytext()); /* Ignorar */ }

/* Inicio de comentario múltiple */
<YYINITIAL> "¡"             { yybegin(COMMENT_MULTI); }

/* Manejo de comentarios múltiples */
<COMMENT_MULTI> {
    "!"                     { yybegin(YYINITIAL); }
    [^]                     { /* Ignorar cualquier carácter */ }
}

/* Inicio de string literal */
<YYINITIAL> "\""            { stringLiteral.setLength(0); yybegin(STRING); }

/* Manejo de string literal */
<STRING> {
    "\""                    { yybegin(YYINITIAL); 
                              debug("LIT_STRING", stringLiteral.toString()); 
                              return symbol(sym.LIT_STRING, stringLiteral.toString()); }
    "\\\""                  { stringLiteral.append('\"'); }
    "\\\\"                  { stringLiteral.append('\\'); }
    [^\"]                   { stringLiteral.append(yytext()); }
}

/* Inicio de char literal */
<YYINITIAL> "'"             { yybegin(CHAR); }

/* Manejo de char literal */
<CHAR> {
    "'"                     { yybegin(YYINITIAL); 
                              debug("LIT_CHAR", yytext()); 
                              return symbol(sym.LIT_CHAR, yytext().charAt(0)); }
    .                       { /* Solo un carácter */ }
}

/* Espacios en blanco */
<YYINITIAL> {Espacio}       { /* Ignorar */ }

/* Cualquier otro carácter */
<YYINITIAL> .               { debug("ERROR", yytext()); return symbol(sym.ERROR); }

/* Error fallback */
[^] { throw new Error("Carácter ilegal <"+yytext()+"> en línea "+(yyline+1)+", columna "+(yycolumn+1)); }