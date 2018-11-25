package compiler;

public class Token {
	private TipoToken type;
	private String lexeme;
	private int linha;

	public Token(TipoToken type, String lexeme) {
		super();
		this.type = type;
		this.lexeme = lexeme;
	}

	public Token(TipoToken type, String lexeme, int linha) {
		super();
		this.type = type;
		this.lexeme = lexeme;
		this.linha = linha;
	}

	public TipoToken getTipo() {
		return type;
	}

	public String getLexema() {
		return lexeme;
	}

	public int getLinha() {
		return linha - 1;
	}

	@Override
	public String toString() {
		return "Token [type=" + type + ", lexeme=" + lexeme + "]";
	}

}
