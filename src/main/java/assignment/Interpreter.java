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

	private boolean bool;

	public static void main(String args[]){
		loadSpecies("FlyTrap.cri");
	}
	public static void executeCritter(Critter c) {
		// obviously, your code should do something
		String code = c.getCode();
		int startIdx = code.getNextCodeLine();
		bool = false;
		for (int i = startIdx; i < code.size(); i++) {
			String line = code.get(i);
			String[] arr = line.split(" ");
			String str = arr[0];
			i = executeCode(c, str, i);
			if (bool) {
				c.setNextCodeLine(i + 1);
				return;
			}
		}
		return;
	}

	private int executeCode(Critter c, String str, int i) {
		int idx = i;
		switch(str) {
			case "hop":
				c.hop();
				bool = true;
				break;
			case "left":
				c.left();
				bool = true;
				break;
			case "right":
				c.right();
				bool = true;
				break;
			case "infect":
				if (arr.length > 1) {
					c.infect(Integer.parseInt(arr[-1]));
				} else {
					c.infect();
				}
				bool = true;
				break;
			case "eat":
				c.eat();
				bool = true;
				break;
			case "go":
				if (arr[-1].substring(0, 1).equals("+")) {
					idx += Integer.parseInt(arr[-1].substring(1));
					idx--;
				} else if (arr[-1].substring(0, 1).equals("-")) {
					idx -= Integer.parseInt(arr[-1].substring(1));
					idx--;
				} else if (arr[-1].substring(0, 1).equals("r")) {
					executeCode(c, c.getReg(arr[-1], idx));
				} else {
					idx = Integer.parseInt(arr[-1]);
				}
				break;
		}
		return idx;
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
