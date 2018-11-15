package compiler;

public class Yytoken {
	private Token type;
	private String lexeme;
	
	public Yytoken(Token type, String lexeme) {
		super();
		this.type = type;
		this.lexeme = lexeme;
	}

	public Token getType() {
		return type;
	}

	public String getLexeme() {
		return lexeme;
	}

	@Override
	public String toString() {
		return "Yytoken [type=" + type + ", lexeme=" + lexeme + "]";
	}

}
