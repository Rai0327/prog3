package assignment;

import java.util.ArrayList;
import java.util.List;

public class CritterImplementation implements Critter {

    private List code;
    private int codeLine;
    private int[] regList;
    private Critter.HungerLevel hungerLevel;
    private int heading;
    private ArrayList<String> executedCode;


    public CritterImplementation(List code) {
        this.code = code;
        codeLine = 0;
        regList = new int[10];
        hungerLevel = HungerLevel.SATISFIED;
        heading = 0;
        executedCode = new ArrayList<String>();
    }

    public List getCode() {
        return code;
    }

    public int getNextCodeLine() {
        return codeLine;
    }

    public void setNextCodeLine(int line) {
        codeLine = line;
    }

    public int getReg(int n) {
        executedCode.add("getReg");
        return regList[n - 1];
    }

    public void setReg(int n, int value) {
        executedCode.add("setReg");
        regList[n - 1] = value;
    }

    public Critter.HungerLevel getHungerLevel() {
        executedCode.add("getHungerLevel");
        return hungerLevel;
    }

    public void hop() {
        executedCode.add("hop");
    }

    public void left() {
        executedCode.add("left");
    }

    public void right() {
        executedCode.add("right");
    }

    public void eat() {
        executedCode.add("eat");
    }

    public void infect() {
        executedCode.add("infect");
    }

    public void infect(int n) {
        executedCode.add("infect " + Integer.toString(n));
    }

    public int getCellContent(int bearing) {
        executedCode.add("getCellContent");
        return 0;
    }

    public int getOffAngle(int bearing) {
        executedCode.add("getOffAngle");
        return Math.abs(heading - bearing);
    }

    public boolean ifRandom() {
        executedCode.add("ifRandom");
        return Math.random() < 0.5;
    }

    public ArrayList<String> getExecutedCode() {
        return executedCode;
    }
}
