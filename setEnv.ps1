java -jar lib\jflex-full-1.9.1.jar src\Lexer.flex -d src\
java -jar lib\java-cup-11b.jar -parser Parser -symbols sym -destdir src src\Parser.cup
javac -d bin -cp "lib/*" src\*.java
java -cp "bin;lib/*" Parser