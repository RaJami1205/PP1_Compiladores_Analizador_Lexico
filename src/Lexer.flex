/* ================== Opciones ================== */
%class Lexer
%unicode
%cup
%line
%column

%%

/* ================== Expresiones regulares ================== */
DIGITO  = [0-9]
LETRA   = [a-zA-Z]
ID      = {LETRA}({LETRA}|{DIGITO}|_)*

%%

/* ================== Reglas ================== */
"let"        { return new java_cup.runtime.Symbol(sym.LET); }
[a-zA-Z_][a-zA-Z0-9_]*  { return new java_cup.runtime.Symbol(sym.ID, yytext()); }
{DIGITO}+    { return new java_cup.runtime.Symbol(sym.NUM, Integer.parseInt(yytext())); }
";"          { return new java_cup.runtime.Symbol(sym.PYC); }

/* Comentarios de una línea */
"|"[^\n]*    { /* ignorar */ }

/* Comentarios de múltiples líneas */
"¡"[^!]*"!"  { /* ignorar */ }

/* Espacios en blanco */
[ \t\r\n]+   { /* ignorar */ }

/* Caracter ilegal */
.            { System.err.println("Caracter ilegal: " + yytext()); }

Prueba de commit