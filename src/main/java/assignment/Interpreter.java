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

	public void executeCritter(Critter c) {
		// obviously, your code should do something
		List code = c.getCode();
		int startIdx = c.getNextCodeLine();
		for (int i = startIdx; i < code.size(); i++) {
			String line = (String) code.get(i);
			String[] arr = line.split(" ");
			String str = arr[0];
			switch (str) {
				case "hop":
					c.hop();
					c.setNextCodeLine(i + 1);
					return;
				case "left":
					c.left();
					c.setNextCodeLine(i + 1);
					return;
				case "right":
					c.right();
					c.setNextCodeLine(i + 1);
					return;
				case "infect":
					if (arr.length > 1) {
						c.infect(Integer.parseInt(arr[arr.length - 1]));
					} else {
						c.infect();
					}
					c.setNextCodeLine(i + 1);
					return;
				case "eat":
					c.eat();
					c.setNextCodeLine(i + 1);
					return;
				case "go":
					if (arr[arr.length - 1].substring(0, 1).equals("+")) {
						i += Integer.parseInt(arr[arr.length - 1].substring(1)) - 1;
					} else if (arr[arr.length - 1].substring(0, 1).equals("-")) {
						i -= Integer.parseInt(arr[arr.length - 1].substring(1)) - 1;
					} else if (arr[arr.length - 1].substring(0, 1).equals("r")) {
						i = c.getReg(Integer.parseInt(arr[arr.length - 1].substring(1))) - 1;
					} else {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifrandom":
					if (c.ifRandom()) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifhungry":
					if (c.getHungerLevel() == Critter.HungerLevel.valueOf("HUNGRY")
							|| c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifstarving":
					if (c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifempty":
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.EMPTY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifally":
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.ALLY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifenemy":
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.ENEMY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifwall":
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.WALL) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifangle":
					if (c.getOffAngle(Integer.parseInt(arr[1])) == Integer.parseInt(arr[2])) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "write":
					c.setReg(Integer.parseInt(arr[1]), Integer.parseInt(arr[arr.length - 1]));
					break;
				case "add":
					c.setReg(Integer.parseInt(arr[1]), c.getReg(Integer.parseInt(arr[1])) + c.getReg(Integer.parseInt(arr[arr.length - 1])));
					break;
				case "sub":
					c.setReg(Integer.parseInt(arr[1]), c.getReg(Integer.parseInt(arr[1])) - c.getReg(Integer.parseInt(arr[arr.length - 1])));
					break;
				case "inc":
					c.setReg(Integer.parseInt(arr[arr.length - 1]), c.getReg(Integer.parseInt(arr[arr.length - 1])) + 1);
					break;
				case "dec":
					c.setReg(Integer.parseInt(arr[arr.length - 1]), c.getReg(Integer.parseInt(arr[arr.length - 1])) - 1);
					break;
				case "iflt":
					if (c.getReg(Integer.parseInt(arr[1])) < c.getReg(Integer.parseInt(arr[2]))) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifeq":
					if (c.getReg(Integer.parseInt(arr[1])) == c.getReg(Integer.parseInt(arr[2]))) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
				case "ifgt":
					if (c.getReg(Integer.parseInt(arr[1])) > c.getReg(Integer.parseInt(arr[2]))) {
						i = Integer.parseInt(arr[arr.length - 1]) - 2;
					}
					break;
			}
		}
	}

	public CritterSpecies loadSpecies(String filename) throws IOException {
		// obviously, your code should do something
		List code = new ArrayList<String>();
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fileReader);
			String name = reader.readLine();
			String line;
			while (!(line = reader.readLine()).equals("")) {
				code.add(line);
			}
			CritterSpecies crit = new CritterSpecies(name, code);
			return crit;
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}
}
