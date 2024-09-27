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
		HashSet<Integer> angleSet = new HashSet<Integer>();
		for (int num = 0; num <= 315; num += 45) {
			angleSet.add(num);
		}
		if (c.getCode() == null) {
			System.err.println("Null code list");
			return;
		}
		if (c.getCode().size() <= 0) {
			return;
		}
		if (c.getNextCodeLine() <= 0) {
			System.err.println("Invalid initial next code line");
		}
		List code = c.getCode();
		int startIdx = c.getNextCodeLine();
		for (int i = startIdx; i <= code.size(); i++) {
			if (code.get(i - 1) == null) {
				System.err.println("Null code list element");
				return;
			}
			String line = (String) code.get(i - 1);
			String[] arr = line.split(" ");
			String str = arr[0];
			switch (str) {
				case "hop":
					if (arr.length != 1) {
						System.err.println("Invalid hop parameter setup");
						return;
					}
					c.hop();
					c.setNextCodeLine(i + 1);
					return;
				case "left":
					if (arr.length != 1) {
						System.err.println("Invalid left parameter setup");
						return;
					}
					c.left();
					c.setNextCodeLine(i + 1);
					return;
				case "right":
					if (arr.length != 1) {
						System.err.println("Invalid right parameter setup");
						return;
					}
					c.right();
					c.setNextCodeLine(i + 1);
					return;
				case "infect":
					if (arr.length > 2) {
						System.err.println("Invalid infect parameter setup");
						return;
					}
					if (arr.length > 1) {
						int n = Integer.parseInt(arr[arr.length - 1]);
						if (n >= code.size() || n <= 0) {
							System.err.println("Invalid parameter input for infect at line " + i);
						}
						c.infect(n);
					} else {
						c.infect();
					}
					c.setNextCodeLine(i + 1);
					return;
				case "eat":
					if (arr.length != 1) {
						System.err.println("Invalid eat parameter setup");
						return;
					}
					c.eat();
					c.setNextCodeLine(i + 1);
					return;
				case "go":
					if (arr.length != 2) {
						System.err.println("Invalid go parameter setup");
						return;
					}
					String firstChar = arr[arr.length - 1].substring(0, 1);
					if (firstChar.equals("+") || firstChar.equals("-") || firstChar.equals("r")) {
						if (!isParseable(arr[arr.length - 1].substring(1))) {
							System.err.println("Invalid go parameter");
							return;
						}
					} else {
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid go parameter");
							return;
						}
					}
					if (firstChar.equals("+")) {
						i += Integer.parseInt(arr[arr.length - 1].substring(1)) - 1;
					} else if (firstChar.equals("-")) {
						i -= Integer.parseInt(arr[arr.length - 1].substring(1)) - 1;
					} else if (firstChar.equals("r")) {
						i = c.getReg(Integer.parseInt(arr[arr.length - 1].substring(1))) - 1;
					} else {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifrandom":
					if (arr.length != 2) {
						System.err.println("Invalid ifrandom parameter setup");
						return;
					}
					if (c.ifRandom()) {
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid ifrandom parameter");
							return;
						}
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifhungry":
					if (arr.length != 2) {
						System.err.println("Invalid ifhungry parameter setup");
						return;
					}
					if (c.getHungerLevel() == null) {
						System.err.println("Null hunger level");
						return;
					}
					if (c.getHungerLevel() == Critter.HungerLevel.valueOf("HUNGRY")
							|| c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid ifhungry parameter");
							return;
						}
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifstarving":
					if (arr.length != 2) {
						System.err.println("Invalid ifstarving parameter setup");
						return;
					}
					if (c.getHungerLevel() == null) {
						System.err.println("Null hunger level");
						return;
					}
					if (c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid ifstarving parameter");
							return;
						}
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifempty":
					if (arr.length != 3) {
						System.err.println("Invalid ifempty parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifempty parameter");
						return;
					}
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.EMPTY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifally":
					if (arr.length != 3) {
						System.err.println("Invalid ifally parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifally parameter");
						return;
					}
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.ALLY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifenemy":
					if (arr.length != 3) {
						System.err.println("Invalid ifenemy parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifenemy parameter");
						return;
					}
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.ENEMY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifwall":
					if (arr.length != 3) {
						System.err.println("Invalid ifwall parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifwall parameter");
						return;
					}
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.WALL) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifangle":
					if (arr.length != 4) {
						System.err.println("Invalid ifangle parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1])) || !angleSet.contains(Integer.parseInt(arr[2]))) {
						System.err.println("Invalid ifangle parameter");
						return;
					}
					if (c.getOffAngle(Integer.parseInt(arr[1])) == Integer.parseInt(arr[2])) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "write":
					if (arr.length != 3) {
						System.err.println("Invalid write parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid write parameter");
						return;
					}
					int r = Integer.parseInt(arr[1]);
					if (r > 10 || r <= 0) {
						System.err.println("Invalid write parameter");
						return;
					}
					c.setReg(Integer.parseInt(arr[1]), Integer.parseInt(arr[arr.length - 1]));
					break;
				case "add":
					if (arr.length != 3) {
						System.err.println("Invalid add parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid add parameter");
						return;
					}
					int r1 = Integer.parseInt(arr[1]);
					int r2 = Integer.parseInt(arr[arr.length - 1]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid add parameter");
						return;
					}
					c.setReg(Integer.parseInt(arr[1]), c.getReg(Integer.parseInt(arr[1])) + c.getReg(Integer.parseInt(arr[arr.length - 1])));
					break;
				case "sub":
					if (arr.length != 3) {
						System.err.println("Invalid sub parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid sub parameter");
						return;
					}
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[arr.length - 1]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid sub parameter");
						return;
					}
					c.setReg(Integer.parseInt(arr[1]), c.getReg(Integer.parseInt(arr[1])) - c.getReg(Integer.parseInt(arr[arr.length - 1])));
					break;
				case "inc":
					if (arr.length != 2) {
						System.err.println("Invalid inc parameter setup");
						return;
					}
					if (!isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid inc parameter");
						return;
					}
					r = Integer.parseInt(arr[arr.length - 1]);
					if (r > 10 || r <= 0) {
						System.err.println("Invalid inc parameter");
						return;
					}
					c.setReg(Integer.parseInt(arr[arr.length - 1]), c.getReg(Integer.parseInt(arr[arr.length - 1])) + 1);
					break;
				case "dec":
					if (arr.length != 2) {
						System.err.println("Invalid dec parameter setup");
						return;
					}
					if (!isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid dec parameter");
						return;
					}
					r = Integer.parseInt(arr[arr.length - 1]);
					if (r > 10 || r <= 0) {
						System.err.println("Invalid dec parameter");
						return;
					}
					c.setReg(Integer.parseInt(arr[arr.length - 1]), c.getReg(Integer.parseInt(arr[arr.length - 1])) - 1);
					break;
				case "iflt":
					if (arr.length != 4) {
						System.err.println("Invalid iflt parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid iflt parameter");
						return;
					}
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[2]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid iflt parameter");
						return;
					}
					if (c.getReg(Integer.parseInt(arr[1])) < c.getReg(Integer.parseInt(arr[2]))) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifeq":
					if (arr.length != 4) {
						System.err.println("Invalid ifeq parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid ifeq parameter");
						return;
					}
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[2]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid ifeq parameter");
						return;
					}
					if (c.getReg(Integer.parseInt(arr[1])) == c.getReg(Integer.parseInt(arr[2]))) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifgt":
					if (arr.length != 4) {
						System.err.println("Invalid ifgt parameter setup");
						return;
					}
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid ifgt parameter");
						return;
					}
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[2]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid ifgt parameter");
						return;
					}
					if (c.getReg(Integer.parseInt(arr[1])) > c.getReg(Integer.parseInt(arr[2]))) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				default:
					System.err.println("Invalid command");
					return;
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
			System.err.println(e);
			return null;
		}
	}

	private boolean isParseable(String str) {
		String[] intDict = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for (int i = 0; i < str.length(); i++) {
			boolean notInt = true;
			for (String s : intDict) {
				if (str.substring(i, i + 1).equals(s)) {
					notInt = false;
				}
			}
			if (notInt) {
				return false;
			}
		}
		return true;
    }
}