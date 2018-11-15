package compiler;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Parser {
	private BufferedReader in;
	private Lexer l;
	private Yytoken lookAhead;
	private SemanticAnalyzer sa;
	
	public Parser(String archiveName) throws FileNotFoundException, IOException {
		this.in = new BufferedReader(new FileReader(archiveName));
		this.l = new Lexer (in);
		this.lookAhead = l.yylex();
		this.sa = new SemanticAnalyzer();
	}
	
	private void match(Token expected) throws IOException{
		if(expected == lookAhead.getType())
			lookAhead = l.yylex();
		else {
			System.out.println("SYNTACTIC ERROR");
			System.exit(0);
		}
	}

	private void bodyCommand() throws IOException{
		if(lookAhead.getType() == Token.IDENTIFIER) {
			if(!sa.variableExists(lookAhead.getLexeme())) {
				System.out.println("Semantic Error, variable was not declared:" +
									lookAhead.getLexeme());	
				System.exit(0);
			}
			
			match(Token.IDENTIFIER);
			bodyCommand();
		}
		else if(lookAhead.getType() == Token.IF || lookAhead.getType() == Token.DO)
			bodyCommand();
	}
	
	private void declaration() throws IOException{
		match(Token.DATA_TYPE);
		if(sa.variableExists(lookAhead.getLexeme())) {
			System.out.println("Semantic Error, variable was not declared:" +
								lookAhead.getLexeme());
			System.exit(0);
		}
		else
			sa.addVariable(lookAhead);
		match(Token.IDENTIFIER);
		varList();
	}
	
	private void varList() throws IOException{
		if(lookAhead.getType() == Token.ARGUMENT_SEPARATOR)
		{
			match(Token.ARGUMENT_SEPARATOR);
			if(sa.variableExists(lookAhead.getLexeme())) {
				System.out.println("Semantic Error, variable was not declared: " +
									lookAhead.getLexeme());
				System.exit(0);
			}
			else
				sa.addVariable(lookAhead);
			match(Token.IDENTIFIER);
			varList();
		}
		else {
			match(Token.COMMAND_SEPARATOR);
			declaration();
		}
	}
	
	private void body() throws IOException{
		declaration();
		bodyCommand();
	}
	
	
	public void program() throws IOException{
		body();
	}
	
}
