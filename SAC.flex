package parser;
import java_cup.runtime.Symbol;
import static parser.sym.*;
%%

%class SACLexic
%cup
%line
%column
%public

%{
    
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Numero = [0-9]+
String = \"[^\"]*\"
Word = [aA-zZ|0-9]+


%%

<YYINITIAL> {
    
    "Asignar"       {System.out.println(yytext());
                return new Symbol(ASIGNAR, yyline + 1, yycolumn + 1, yytext());}
    "Horario"       {System.out.println(yytext());
                return new Symbol(HORARIO, yyline + 1, yycolumn + 1, yytext());}
    "Curso"         {System.out.println(yytext());
                return new Symbol(CURSO, yyline + 1, yycolumn + 1, yytext());}
    "Salon"         {System.out.println(yytext());
                return new Symbol(SALON, yyline + 1, yycolumn + 1, yytext());}
    "Edificio"      {System.out.println(yytext());
                return new Symbol(EDIFICIO, yyline + 1, yycolumn + 1, yytext());}
    "Catedratico"   {System.out.println(yytext());
                return new Symbol(CATEDRATICO, yyline + 1, yycolumn + 1, yytext());}
    "Estudiante"    {System.out.println(yytext());
                return new Symbol(ESTUDIANTE, yyline + 1, yycolumn + 1, yytext());}
    "Usuario"   {System.out.println(yytext());
                return new Symbol(USER, yyline + 1, yycolumn + 1, yytext());}
    ";"         {System.out.println(yytext());
                return new Symbol(COLON, yyline + 1, yycolumn + 1, yytext());}
    ","         {System.out.println(yytext());
                return new Symbol(COMA, yyline + 1, yycolumn + 1, yytext());}
    "("         {System.out.println(yytext());
                return new Symbol(OPEN_C, yyline + 1, yycolumn + 1, yytext());}
    ")"            {System.out.println(yytext());
                return new Symbol(CLOSE_C, yyline + 1, yycolumn + 1, yytext());}
    {Numero}    {System.out.println("NUMERO: "+yytext());
                return new Symbol(NUMBER, yyline + 1, yycolumn + 1, yytext());}
    {String}    {System.out.println("STRING: "+yytext());
                return new Symbol(STRING, yyline + 1, yycolumn + 1, yytext());}
    {Word}      {System.out.println("WORD: "+yytext());
                return new Symbol(WORD, yyline + 1, yycolumn + 1, yytext());}
    {WhiteSpace} {/*empty*/}
}

[^ "("")" ;","[0-9]\"] {System.out.println("Error: '"+yytext()+"'");}