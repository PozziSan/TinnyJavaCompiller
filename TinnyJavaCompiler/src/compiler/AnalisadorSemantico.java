package compiler;

import java.util.HashMap;

public class AnalisadorSemantico {

	// Tabela de símbolos
	private HashMap <String, TipoToken> tabela;


	public AnalisadorSemantico()
	{
		tabela = new HashMap<>();
	}

	// Verifica se a variável existe na tabela de simbolos
	public boolean existeVariavel(String lexema)
	{
		return (tabela.get(lexema) != null);
	}

	// Adicionar uma variável à tabela
	public void adicionaVariavel(Token t)
	{
		tabela.put(t.getLexema(), t.getTipo());
	}

}