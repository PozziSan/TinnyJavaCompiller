package compiler;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Parser {

	private BufferedReader in;
	private Lexer l;
	private Token tokenLido;
	private AnalisadorSemantico as;

	public Parser(String nomeArquivo) throws FileNotFoundException, IOException
	{
		in = new BufferedReader(new FileReader(nomeArquivo));
		l = new Lexer(in);
		tokenLido = l.getToken();
		as = new AnalisadorSemantico();
	}

	private void match(TipoToken esperado) throws IOException
	{
		// O que eu li, é o que é esperado segundo da sintaxe?
		if(esperado == tokenLido.getTipo())
		{
			// leio o proximo TipoToken
			tokenLido = l.getToken();
		}
		else
		{
			// Informar ERRO SINTATICO !!! E encerrar o programa!.
			System.out.println("ERRO SINTATICO!");
			System.out.println("Esperado: " + esperado);
			System.out.println("Obtido: " + tokenLido.getTipo() + " Lexema: " + tokenLido.getLexema());
			System.out.println("Erro na linha: " + tokenLido.getLinha());
			System.exit(0);
		}
	}

	// Construir a gramática.
	public void programa() throws IOException
	{
		pacote();
		imports();
		classe();
	}

	private void pacote() throws IOException {
		match(TipoToken.PACOTE);
		match(TipoToken.IDENTIFICADOR);
		match(TipoToken.SEPARADOR_COMANDO);
	}

	private void imports() throws IOException {
		if (tokenLido.getTipo() == TipoToken.IMPORT){
			match(TipoToken.IMPORT);
			match(TipoToken.IDENTIFICADOR_COMPOSTO);
			match(TipoToken.SEPARADOR_COMANDO);
			imports();
		}
	}

	private void classe() throws IOException {
		if (tokenLido.getTipo() == TipoToken.MODIFICADOR){
			match(TipoToken.MODIFICADOR);
		}
		if (tokenLido.getTipo() == TipoToken.NOME_RESERVADO){
			match(TipoToken.NOME_RESERVADO);
		}
		match(TipoToken.CLASSE);
		match(TipoToken.IDENTIFICADOR);
		match(TipoToken.ABRE_BLOCO);
		corpoClasse();
		match(TipoToken.FECHA_BLOCO);
	}


	/* corpoClasse -> declaracoesVariaveis metodos

	 */
	private void corpoClasse() throws IOException
	{
		declaracoesVariaveis();
		metodos();
	}


	/*
        CorpoCMD -> <id> <=> Expressao CorpoCMD | ComandoBloco CorpoCMD | $
    */
	private void metodos() throws IOException
	{
		if(tokenLido.getTipo() == TipoToken.IDENTIFICADOR)
		{
			if(!as.existeVariavel(tokenLido.getLexema()))
			{
				System.out.println("ERRO SEMANTICO!");
				System.out.println("Variável não declarada: " + tokenLido.getLexema());
				System.out.println("Linha: " + tokenLido.getLinha());
				System.exit(0);
			}
			match(TipoToken.IDENTIFICADOR);
			// match atribuição
			//   expressao();
		}

		if(tokenLido.getTipo()== TipoToken.IF ||
				tokenLido.getTipo()==TipoToken.WHILE ||
				tokenLido.getTipo()== TipoToken.DO ||
		        tokenLido.getTipo() == TipoToken.FOR)
		{
				bloco();
		}
	}

	private void bloco(){
		if (tokenLido.getTipo() == TipoToken.IF){

		}

		if (tokenLido.getTipo() == TipoToken.WHILE){

		}

		if (tokenLido.getTipo() == TipoToken.DO){

		}

		if (tokenLido.getTipo() == TipoToken.FOR){

		}
	}

	/*  declaracoesVariaveis -> <tipo> <id> listaVar
        listaVar -> <,> <id> listaVar | <;>
    */
	private void declaracoesVariaveis() throws IOException
	{
		if (tokenLido.getTipo() == TipoToken.MODIFICADOR){
			match(TipoToken.MODIFICADOR);
		}
		match(TipoToken.TIPO_DADO);
		if(as.existeVariavel(tokenLido.getLexema())) {
			System.out.println("ERRO SEMANTICO!");
			System.out.println("Variável previamente declarada: " + tokenLido.getLexema());
			System.out.println("Linha: " + tokenLido.getLinha());
			System.exit(0);
		}
		else {
			as.adicionaVariavel(tokenLido);
		}
		match(TipoToken.IDENTIFICADOR);
		if (tokenLido.getTipo() == TipoToken.ATRIBUICAO){
			match(TipoToken.ATRIBUICAO);
			match(TipoToken.CONSTANTE_INTEIRA);
			//Não implementado outros tipos de atribuição!
		}
		listaVar();
	}

	private void listaVar() throws IOException
	{
		if(tokenLido.getTipo() == TipoToken.SEPARADOR_ARGUMENTO)
		{
			match(TipoToken.SEPARADOR_ARGUMENTO);
			if(as.existeVariavel(tokenLido.getLexema()))
			{
				System.out.println("ERRO SEMANTICO!");
				System.out.println("Variável previamente declarada: " + tokenLido.getLexema());
				System.out.println("Linha: " + tokenLido.getLinha());
				System.exit(0);
			}
			else
				as.adicionaVariavel(tokenLido);
			match(TipoToken.IDENTIFICADOR);
			listaVar();
		}
		else
		{
			match(TipoToken.SEPARADOR_COMANDO);
			declaracoesVariaveis();
		}
	}


}
