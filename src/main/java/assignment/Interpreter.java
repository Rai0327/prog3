package assignment;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Responsible for loading critter species from text files and interpreting the
 * simple Critter language.
 * 
 * For more information on the purpose of the below two methods, see the
 * included API/ folder and the project description.
 */
public class Interpreter implements CritterInterpreter {
	public static void main(String args[]){
		loadSpecies("FlyTrap.cri");
	}
	public static void executeCritter(Critter c) {
		// obviously, your code should do something

		return;
	}

	public static CritterSpecies loadSpecies(String filename) throws IOException {
		// obviously, your code should do something
		ArrayList<String> code = new ArrayList<String>();
		FileReader fileReader = new FileReader(inputFilename);
		BufferedReader reader = new BufferedReader(fileReader);
		String name = reader.readLine();
		String line;
		while(!(line = reader.readLine()).equals("")) {
			code.add(line);
		}
		for(int i = 0; i < code.size(); i++) {
			System.out.println(code.get(i));
		}
		CritterSpecies crit = new CritterSpecies(name, code);
		return crit;
	}
}
