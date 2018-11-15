package compilador;
%%
%class Lexer
%{
%}
%eof{
    System.out.println("Fim de arquivo!!!");
    System.exit(0);
%eof}
%%



"int" | "float" | "double" | "char"   
             {
                    return new Yytoken(Token.TIPO_DADO, yytext());
             }

if           {
                    return new Yytoken(Token.IF, yytext());
             }

\p{letter}\w*  {
                    return new Yytoken(Token.IDENTIFICADOR, yytext());
               }

[+\-*/%]       {
                    return new Yytoken(Token.OPERADOR_ARITMETICO, yytext());
               }

\d(\d)*        {
                    return new Yytoken(Token.CONSTANTE_INTEIRA, yytext());
               }

=              {
                    return new Yytoken(Token.ATRIBUICAO, yytext());
               }

;              {
                    return new Yytoken(Token.SEPARADOR_COMANDO, yytext());
               }

,              {
                    return new Yytoken(Token.SEPARADOR_ARGUMENTO, yytext());
               }
\n | \r | \r\n {}  // Ignora quebra de linha.

. {}   // Qualquer caractere ignorar.
