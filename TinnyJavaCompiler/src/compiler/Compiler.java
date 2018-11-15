package compiler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Compiler {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		var parser = new Parser("input.txt");
		parser.program();
		System.out.println("Begging of the sintatic Analysis");
	}

}
