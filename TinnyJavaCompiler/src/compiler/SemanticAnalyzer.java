package compiler;

import java.util.HashMap;

public class SemanticAnalyzer {
	private HashMap <String, Token> table;
	
	public SemanticAnalyzer() {
		this.table = new HashMap<>();
	}
	
	public boolean variableExists(String lexeme) {
		return (this.table.get(lexeme) != null);
	}
	
	public void addVariable(Yytoken t) {
		this.table.put(t.getLexeme(), t.getType());
	}
}
