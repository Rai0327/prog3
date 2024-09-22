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

	private static boolean bool;

	public static void executeCritter(Critter c) {
		// obviously, your code should do something
		List code = c.getCode();
		int startIdx = c.getNextCodeLine();
		bool = false;
		for (int i = startIdx; i < code.size(); i++) {
			String line = (String) code.get(i);
			i = executeCode(c, line, i);
			if (bool) {
				c.setNextCodeLine(i + 1);
				return;
			}
		}
	}

	private static int executeCode(Critter c, String line, int i) {
		String[] arr = line.split(" ");
		String str = arr[0];
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
					idx += Integer.parseInt(arr[-1].substring(1)) - 1;
				} else if (arr[-1].substring(0, 1).equals("-")) {
					idx -= Integer.parseInt(arr[-1].substring(1)) - 1;
				} else if (arr[-1].substring(0, 1).equals("r")) {
					idx = c.getReg(Integer.parseInt(arr[-1].substring(1))) - 1;
				} else {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifrandom":
				if(c.ifRandom()) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifhungry":
				if (c.getHungerLevel() == Critter.HungerLevel.valueOf("HUNGRY")
						|| c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifstarving":
				if (c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifempty":
				if (c.getCellContent(Integer.parseInt(arr[1])) == c.EMPTY) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifally":
				if (c.getCellContent(Integer.parseInt(arr[1])) == c.ALLY) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifenemy":
				if (c.getCellContent(Integer.parseInt(arr[1])) == c.ENEMY) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifwall":
				if (c.getCellContent(Integer.parseInt(arr[1])) == c.WALL) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifangle":
				if (c.getOffAngle(Integer.parseInt(arr[1])) == Integer.parseInt(arr[2])) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "write":
				c.setReg(Integer.parseInt(arr[1]), Integer.parseInt(arr[-1]));
				break;
			case "add":
				c.setReg(Integer.parseInt(arr[1]), c.getReg(Integer.parseInt(arr[1])) + c.getReg(Integer.parseInt(arr[-1])));
				break;
			case "sub":
				c.setReg(Integer.parseInt(arr[1]), c.getReg(Integer.parseInt(arr[1])) - c.getReg(Integer.parseInt(arr[-1])));
				break;
			case "inc":
				c.setReg(Integer.parseInt(arr[-1]), c.getReg(Integer.parseInt(arr[-1])) + 1);
				break;
			case "dec":
				c.setReg(Integer.parseInt(arr[-1]), c.getReg(Integer.parseInt(arr[-1])) - 1);
				break;
			case "iflt":
				if (c.getReg(Integer.parseInt(arr[1])) < c.getReg(Integer.parseInt(arr[2]))) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifeq":
				if (c.getReg(Integer.parseInt(arr[1])) == c.getReg(Integer.parseInt(arr[2]))) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
			case "ifgt":
				if (c.getReg(Integer.parseInt(arr[1])) > c.getReg(Integer.parseInt(arr[2]))) {
					idx = Integer.parseInt(arr[-1]) - 1;
				}
				break;
		}
		return idx;
	}

	public static CritterSpecies loadSpecies(String filename) throws IOException {
		// obviously, your code should do something
		ArrayList<String> code = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fileReader);
			String name = reader.readLine();
			String line;
			while (!(line = reader.readLine()).equals("")) {
				code.add(line);
			}
			for (int i = 0; i < code.size(); i++) {
				System.out.println(code.get(i));
			}
			CritterSpecies crit = new CritterSpecies(name, code);
			return crit;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}
}
