package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Compiler {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Parser parser = new Parser("TinnyJavaCompiler/input.txt");
		parser.programa();
		System.out.println("Begging of the sintatic Analysis");
	}

}
