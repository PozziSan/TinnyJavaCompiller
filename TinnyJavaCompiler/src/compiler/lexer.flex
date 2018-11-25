package compiler;
%%
%class Lexer
%{
%}
%eof{
    System.out.println("Fim de arquivo!!!");
    System.exit(0);
%eof}
%function getToken
%type Token
%%


package      {
                    return new Token(TipoToken.PACOTE, yytext(), yyline);
             }

import       {
                    return new Token(TipoToken.IMPORT, yytext(), yyline);
             }

class       {
                    return new Token(TipoToken.CLASSE, yytext(), yyline);
             }

"static" | "void"
             {
                    return new Token(TipoToken.NOME_RESERVADO, yytext(), yyline);
             }

"int" | "float" | "double" | "char" | "String"
             {
                    return new Token(TipoToken.TIPO_DADO, yytext(), yyline);
             }

"public" | "private" | "protected"
             {
                    return new Token(TipoToken.MODIFICADOR, yytext(), yyline);
             }

if           {
                    return new Token(TipoToken.IF, yytext(), yyline);
             }

else         {
                    return new Token(TipoToken.ELSE, yytext(), yyline);
             }

for          {
                    return new Token(TipoToken.FOR, yytext(), yyline);
             }

while        {
                    return new Token(TipoToken.WHILE, yytext(), yyline);
             }

do           {
                    return new Token(TipoToken.DO, yytext(), yyline);
             }

\p{letter}\w*\.\p{letter}\w*(\.\w*)*
               {
                    return new Token(TipoToken.IDENTIFICADOR_COMPOSTO, yytext(), yyline);
               }

\p{letter}\w*  {
                    return new Token(TipoToken.IDENTIFICADOR, yytext(), yyline);
               }

[+\-*/%]       {
                    return new Token(TipoToken.OPERADOR_ARITMETICO, yytext(), yyline);
               }

[({]       {
                    return new Token(TipoToken.ABRE_BLOCO, yytext(), yyline);
               }

[)}]       {
                    return new Token(TipoToken.FECHA_BLOCO, yytext(), yyline);
               }

\d(\d)*        {
                    return new Token(TipoToken.CONSTANTE_INTEIRA, yytext(), yyline);
               }

=              {
                    return new Token(TipoToken.ATRIBUICAO, yytext(), yyline);
               }

;              {
                    return new Token(TipoToken.SEPARADOR_COMANDO, yytext(), yyline);
               }

,              {
                    return new Token(TipoToken.SEPARADOR_ARGUMENTO, yytext(), yyline);
               }
\n | \r | \r\n
{
    yyline++;
}  // Ignora quebra de linha.

. {}   // Qualquer caractere ignorar.
