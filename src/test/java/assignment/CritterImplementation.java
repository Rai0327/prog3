package assignment;

import java.util.List;

public class CritterImplementation implements Critter {

    private List code;
    private int codeLine;
    private int[] regList;
    private Critter.HungerLevel hungerLevel;
    private int heading;


    public CritterImplementation(List code) {
        this.code = code;
        codeLine = 0;
        regList = new int[10];
        hungerLevel = HungerLevel.SATISFIED;
        heading = 0;
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
        return regList[n - 1];
    }

    public void setReg(int n, int value) {
        regList[n - 1] = value;
    }

    public Critter.HungerLevel getHungerLevel() {
        return hungerLevel;
    }

    public void hop() {
        System.out.println("Hop");
    }

    public void left() {
        System.out.println("Left");
    }

    public void right() {
        System.out.println("Right");
    }

    public void eat() {
        System.out.println("Eat");
    }

    public void infect() {
        System.out.println("Infect");
    }

    public void infect(int n) {
        System.out.println("Infect " + Integer.toString(n));
    }

    public int getCellContent(int bearing) {
        return 0;
    }

    public int getOffAngle(int bearing) {
        return Math.abs(heading - bearing);
    }

    public boolean ifRandom() {
        return Math.random() < 0.5;
    }
}
