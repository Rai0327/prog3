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

	private boolean isParseable(String str) {
		//Creates list of valid digits
		String[] intDict = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

		//Iterates and checks the string is an integer when parsed
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

	public void executeCritter(Critter c) {
		//Checks for null Critter
		if (c == null) {
			System.err.println("Null critter");
			return;
		}

		//Set of valid bearings
		HashSet<Integer> angleSet = new HashSet<Integer>();
		for (int num = 0; num <= 315; num += 45) {
			angleSet.add(num);
		}

		//Checks that list exists
		if (c.getCode() == null) {
			System.err.println("Null code list");
			return;
		}

		//Checks for empty list
		if (c.getCode().size() <= 0) {
			System.err.println("Empty code list");
			return;
		}

		//Checks the start index is valid
		if (c.getNextCodeLine() <= 0) {
			System.err.println("Invalid next code line");
			return;
		}

		//Initialize list and starting index of the list
		List code = c.getCode();
		int startIdx = c.getNextCodeLine();

		//Iterates through Critter actions/checks during simulation, uses index i as the line number
		//Checks that line number is between 1 and code.size() inclusive at all times
		for (int i = startIdx; true; i++) {

			if (i > code.size() && i <= 0) {
				System.err.println("Line value out of bounds");
				return;
			}

			//Checks for null list elements
			if (code.get(i - 1) == null) {
				System.err.println("Null code list element");
				return;
			}

			//Checks list contains String values
			if (!code.get(i - 1).getClass().getName().equals("java.lang.String")) {
				System.err.println("Code element of invalid type");
				return;
			}

			//Reads line and places parameters into an array
			String line = (String) code.get(i - 1);
			String[] arr = line.split(" ");

			//Stores behavior name in a string
			String str = arr[0];

			//Implements the corresponding behavior
			switch (str) {
				case "hop":
					//Checks the hop call only contains one parameter
					if (arr.length != 1) {
						System.err.println("Invalid hop parameter setup");
						return;
					}
					c.hop();
					c.setNextCodeLine(i + 1);
					return;
				case "left":
					//Checks the left call only contains one parameter
					if (arr.length != 1) {
						System.err.println("Invalid left parameter setup");
						return;
					}
					c.left();
					c.setNextCodeLine(i + 1);
					return;
				case "right":
					//Checks the right call only contains one parameter
					if (arr.length != 1) {
						System.err.println("Invalid right parameter setup");
						return;
					}
					c.right();
					c.setNextCodeLine(i + 1);
					return;
				case "infect":
					//Checks the infect call contains at most 2 parameters
					if (arr.length > 2) {
						System.err.println("Invalid infect parameter setup");
						return;
					}
					//Checks the second parameter to be a valid line call
					if (arr.length > 1) {
						int n = Integer.parseInt(arr[arr.length - 1]);
						//Parameter should be in range (1,code.size())
						if (n > code.size() || n <= 0) {
							System.err.println("Invalid parameter input for infect");
						}
						c.infect(n);
					} else {
						c.infect();
					}
					c.setNextCodeLine(i + 1);
					return;
				case "eat":
					//Checks the eat call only contains one parameter
					if (arr.length != 1) {
						System.err.println("Invalid eat parameter setup");
						return;
					}
					c.eat();
					c.setNextCodeLine(i + 1);
					return;
				case "go":
					//Checks the go call contains two parameters
					if (arr.length != 2) {
						System.err.println("Invalid go parameter setup");
						return;
					}
					//Checks for prefix
					String firstChar = arr[arr.length - 1].substring(0, 1);
					if (firstChar.equals("+") || firstChar.equals("-") || firstChar.equals("r")) {
						//Checks that an integer follows the prefix
						if (!isParseable(arr[arr.length - 1].substring(1))) {
							System.err.println("Invalid go parameter");
							return;
						}
					} else {
						//Checks for an integer line number
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid go parameter");
							return;
						}
					}

					if (firstChar.equals("+")) {
						//Relative jump forward
						i += Integer.parseInt(arr[arr.length - 1].substring(1)) - 1;
					} else if (firstChar.equals("-")) {
						//Relative jump backward
						i -= Integer.parseInt(arr[arr.length - 1].substring(1)) - 1;
					} else if (firstChar.equals("r")) {
						//Checks register value is from 1-10
						int r = Integer.parseInt(arr[arr.length - 1].substring(1));
						if (r > 10 || r <= 0) {
							System.err.println("Invalid write parameter");
							return;
						}
						i = c.getReg(r) - 1;
					} else {
						//Absolute jump to new line
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifrandom":
					//Checks the ifrandom call contains 2 parameters
					if (arr.length != 2) {
						System.err.println("Invalid ifrandom parameter setup");
						return;
					}
					if (c.ifRandom()) {
						//Checks for an integer line number
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid ifrandom parameter");
							return;
						}
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifhungry":
					//Checks the ifhungry call contains two parameters
					if (arr.length != 2) {
						System.err.println("Invalid ifhungry parameter setup");
						return;
					}
					//Checks for null hunger level
					if (c.getHungerLevel() == null) {
						System.err.println("Null hunger level");
						return;
					}
					//Checks if hunger level is hungry or starving
					if (c.getHungerLevel() == Critter.HungerLevel.valueOf("HUNGRY")
							|| c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
						//Checks line input for jump is valid
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid ifhungry parameter");
							return;
						}
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifstarving":
					//Checks the ifstarving call contains two parameters
					if (arr.length != 2) {
						System.err.println("Invalid ifstarving parameter setup");
						return;
					}
					//Checks for null hunger level
					if (c.getHungerLevel() == null) {
						System.err.println("Null hunger level");
						return;
					}
					//Checks for starving hunger level
					if (c.getHungerLevel() == Critter.HungerLevel.valueOf("STARVING")) {
						//Checks line input for jump is valid
						if (!isParseable(arr[arr.length - 1])) {
							System.err.println("Invalid ifstarving parameter");
							return;
						}
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifempty":
					//Checks the ifempty call contains three parameters
					if (arr.length != 3) {
						System.err.println("Invalid ifempty parameter setup");
						return;
					}
					//Checks for integer parameters and a valid bearing input
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifempty parameter");
						return;
					}
					//Jumps to line input if the cell at the bearing is empty
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.EMPTY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifally":
					//Checks the ifally call contains three parameters
					if (arr.length != 3) {
						System.err.println("Invalid ifally parameter setup");
						return;
					}
					//Checks for integer parameters and a valid bearing input
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifally parameter");
						return;
					}
					//Jumps to line input if the cell at the bearing contains an ally
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.ALLY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifenemy":
					//Checks the ifenemy call contains three parameters
					if (arr.length != 3) {
						System.err.println("Invalid ifenemy parameter setup");
						return;
					}
					//Checks for integer parameters and a valid bearing input
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifenemy parameter");
						return;
					}
					//Jumps to line input if the cell at the bearing contains an enemy
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.ENEMY) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifwall":
					//Checks the ifwall call contains three parameters
					if (arr.length != 3) {
						System.err.println("Invalid ifwall parameter setup");
						return;
					}
					//Checks for integer parameters and a valid bearing input
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1]))) {
						System.err.println("Invalid ifwall parameter");
						return;
					}
					//Jumps to line input if the cell at the bearing contains a wall
					if (c.getCellContent(Integer.parseInt(arr[1])) == c.WALL) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifangle":
					//Checks the ifangle call contains four parameters
					if (arr.length != 4) {
						System.err.println("Invalid ifangle parameter setup");
						return;
					}
					//Checks for integer parameters and a valid bearing input
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1]) || !angleSet.contains(Integer.parseInt(arr[1])) || !angleSet.contains(Integer.parseInt(arr[2]))) {
						System.err.println("Invalid ifangle parameter");
						return;
					}
					//Jumps to line input if bearings match
					if (c.getOffAngle(Integer.parseInt(arr[1])) == Integer.parseInt(arr[2])) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "write":
					//Checks the write call contains four parameters
					if (arr.length != 3) {
						System.err.println("Invalid write parameter setup");
						return;
					}
					//Checks for integer parameters
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid write parameter");
						return;
					}
					//Checks the register is a number from 1 to 10
					int r = Integer.parseInt(arr[1]);
					if (r > 10 || r <= 0) {
						System.err.println("Invalid write parameter");
						return;
					}
					//Writes the integer parameter into the register
					c.setReg(r, Integer.parseInt(arr[arr.length - 1]));
					break;
				case "add":
					//Checks the add call contains three parameters
					if (arr.length != 3) {
						System.err.println("Invalid add parameter setup");
						return;
					}
					//Checks for integer parameters
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid add parameter");
						return;
					}
					//Checks both register inputs are numbers from 1 to 10
					int r1 = Integer.parseInt(arr[1]);
					int r2 = Integer.parseInt(arr[arr.length - 1]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid add parameter");
						return;
					}
					c.setReg(r1, r1 + r2);
					break;
				case "sub":
					//Checks the sub call contains three parameters
					if (arr.length != 3) {
						System.err.println("Invalid sub parameter setup");
						return;
					}
					//Checks for integer parameters
					if (!isParseable(arr[1]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid sub parameter");
						return;
					}
					//Checks both register inputs are numbers from 1 to 10
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[arr.length - 1]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid sub parameter");
						return;
					}
					c.setReg(r1, r1-r2);
					break;
				case "inc":
					//Checks the inc call contains two parameters
					if (arr.length != 2) {
						System.err.println("Invalid inc parameter setup");
						return;
					}
					//Checks for integer parameter
					if (!isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid inc parameter");
						return;
					}
					//Checks the register input is a number from 1 to 10
					r = Integer.parseInt(arr[arr.length - 1]);
					if (r > 10 || r <= 0) {
						System.err.println("Invalid inc parameter");
						return;
					}
					c.setReg(r, r + 1);
					break;
				case "dec":
					//Checks the dec call contains two parameters
					if (arr.length != 2) {
						System.err.println("Invalid dec parameter setup");
						return;
					}
					//Checks for integer parameter
					if (!isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid dec parameter");
						return;
					}
					//Checks the register input is a number from 1 to 10
					r = Integer.parseInt(arr[arr.length - 1]);
					if (r > 10 || r <= 0) {
						System.err.println("Invalid dec parameter");
						return;
					}
					c.setReg(r, r - 1);
					break;
				case "iflt":
					//Checks the iflt call contains four parameters
					if (arr.length != 4) {
						System.err.println("Invalid iflt parameter setup");
						return;
					}
					//Checks for integer parameters
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid iflt parameter");
						return;
					}
					//Checks both register inputs are numbers from 1 to 10
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[2]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid iflt parameter");
						return;
					}
					if (r1 < r2) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifeq":
					//Checks the ifeq call contains four parameters
					if (arr.length != 4) {
						System.err.println("Invalid ifeq parameter setup");
						return;
					}
					//Checks for integer parameters
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid ifeq parameter");
						return;
					}
					//Checks both register inputs are numbers from 1 to 10
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[2]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid ifeq parameter");
						return;
					}
					if (r1 == r2) {
						i = Integer.parseInt(arr[arr.length - 1]) - 1;
					}
					break;
				case "ifgt":
					//Checks the ifgt call contains four parameters
					if (arr.length != 4) {
						System.err.println("Invalid ifgt parameter setup");
						return;
					}
					//Checks for integer parameters
					if (!isParseable(arr[1]) || !isParseable(arr[2]) || !isParseable(arr[arr.length - 1])) {
						System.err.println("Invalid ifgt parameter");
						return;
					}
					//Checks both register inputs are numbers from 1 to 10
					r1 = Integer.parseInt(arr[1]);
					r2 = Integer.parseInt(arr[2]);
					if (r1 > 10 || r1 <= 0 || r2 > 10 || r2 <= 0) {
						System.err.println("Invalid ifgt parameter");
						return;
					}
					if (r1 > r2) {
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
		List code = new ArrayList<String>();
		//Check for IOException
		try {
			//Instantiates FileReader and BufferedReader
			FileReader fileReader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fileReader);
			//Reads in name of the Critter separately
			String name = reader.readLine();
			String line;
			//BufferedReader reads in the text file line by line
			//Stops when the file ends or when BufferedReader reaches the description section
			while ((line = reader.readLine()) != null && !(line.equals(""))) {
				code.add(line);
			}
			CritterSpecies crit = new CritterSpecies(name, code);
			return crit;
		} catch (IOException e) {
			System.err.println(e);
			return null;
		}
	}

}