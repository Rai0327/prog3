package assignment;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class CritterTest {

    // Test the executeCritter method for a null input
    @Test
    public void nullCritter() {
        Interpreter interpreter = new Interpreter();
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(null);
        }
    }

    // Test the executeCritter method when the code list is null
    @Test
    public void nullCode() {
        Interpreter interpreter = new Interpreter();
        CritterImplementation critter = new CritterImplementation(null);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the code list is empty
    @Test
    public void emptyCode() {
        Interpreter interpreter = new Interpreter();
        CritterImplementation critter = new CritterImplementation(new ArrayList<String>());
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when an element of the code list is empty
    @Test
    public void nullCodeElement() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add(null);
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the set next code line is invalid (-1)
    @Test
    public void invalidGetNextCodeLine() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Hop.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        critter.setNextCodeLine(-1);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the code list has elements that are not Strings
    @Test
    public void invalidCodeType() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<Integer>();
        temp.add(0);
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the hop instruction has too many parameters
    @Test
    public void invalidHopParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("hop 0 1 2");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the left instruction has too many parameters
    @Test
    public void invalidLeftParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("left 0 1 2");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the right instruction has too many parameters
    @Test
    public void invalidRightParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("right 0 1 2");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when infect has invalid or incorrectly formatted parameters
    @Test
    public void invalidInfectParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("infect 0 1 2");
        temp.add("infect hello have a good day :)");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when eat has too many parameters
    @Test
    public void invalidEatParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("eat 0 1 2");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when go has invalid or incorrectly formatted parameters
    @Test
    public void invalidGoParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("go");
        temp.add("go 0 1 2");
        temp.add("go +gibberish");
        temp.add("go Pair programming is a style of programming in which two programmers work side-by-side at one computer, continuously collaborating on the same design, algorithm, code or test. As discussed below, use of this practice has been demonstrated to improve productivity and quality of software products. Additionally, based on a survey(Williams 1999) of pair programmers (hereafter referred to as “the pair programming survey\"), 100% agreed that they had more confidence in their solution when pair programming than when they program alone. Likewise, 96% agreed that they enjoy their job more than when programming alone.");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifrandom has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfRandomParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifrandom");
        temp.add("ifrandom 0 1 2");
        temp.add("ifrandom jsr125giepn");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifhungry has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfHungryParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifhungry");
        temp.add("ifhungry 0 1 2");
        temp.add("ifhungry jsr125giepn");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifstarving has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfStarvingParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifstarving");
        temp.add("ifstarving 0 1 2");
        temp.add("ifstarving jsr125giepn");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifempty has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfEmptyParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifempty");
        temp.add("ifempty 0");
        temp.add("ifempty 45 hello");
        temp.add("ifempty 44 1");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifally has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfAllyParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifally");
        temp.add("ifally 0");
        temp.add("ifally 45 hello");
        temp.add("ifally 44 1");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifenemy has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfEnemyParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifenemy");
        temp.add("ifenemy 0");
        temp.add("ifenemy 45 hello");
        temp.add("ifenemy 44 1");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifwall has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfWallParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifwall");
        temp.add("ifwall 0");
        temp.add("ifwall 45 hello");
        temp.add("ifwall 44 1");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifangle has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfAngleParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifangle");
        temp.add("ifangle 0 1");
        temp.add("ifangle hi good morning");
        temp.add("ifangle 0 353 1");
        temp.add("ifangle 0 0 -6");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when write has invalid or incorrectly formatted parameters
    @Test
    public void invalidWriteParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("write");
        temp.add("write 9485 ");
        temp.add("write things down");
        temp.add("write 89327149087234892 2");
        temp.add("write -0 2");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when add has invalid or incorrectly formatted parameters
    @Test
    public void invalidAddParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("add");
        temp.add("add 2");
        temp.add("add -20 3");
        temp.add("add 3 11");
        temp.add("add 0 0");
        temp.add("add 1 1");
        temp.add("add is nonparseable");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when sub has invalid or incorrectly formatted parameters
    @Test
    public void invalidSubParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("sub");
        temp.add("sub 2");
        temp.add("sub -20 3");
        temp.add("sub 3 11");
        temp.add("sub 0 0");
        temp.add("sub 1 1");
        temp.add("sub is nonparseable");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when inc has invalid or incorrectly formatted parameters
    @Test
    public void invalidIncParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("inc");
        temp.add("inc 2 1");
        temp.add("inc -20");
        temp.add("inc 11");
        temp.add("inc 0");
        temp.add("inc rement");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when dec has invalid or incorrectly formatted parameters
    @Test
    public void invalidDecParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("dec");
        temp.add("dec 2 1");
        temp.add("dec -20");
        temp.add("dec 11");
        temp.add("dec 0");
        temp.add("dec cremenet");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when iflt has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfLTParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("iflt");
        temp.add("iflt 2");
        temp.add("iflt -20 1 1");
        temp.add("iflt 1 11 1");
        temp.add("iflt 3 9 -3");
        temp.add("iflt 3 9 27");
        temp.add("iflt a number here");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifeq has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfEQParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifeq");
        temp.add("ifeq 2");
        temp.add("ifeq -20 1 1");
        temp.add("ifeq 1 11 1");
        temp.add("ifeq 3 9 -3");
        temp.add("ifeq 3 9 27");
        temp.add("ifeq a number here");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when ifgt has invalid or incorrectly formatted parameters
    @Test
    public void invalidIfGTParams() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("ifgt");
        temp.add("ifgt 2");
        temp.add("ifgt -20 1 1");
        temp.add("ifgt 1 11 1");
        temp.add("ifgt 3 9 -3");
        temp.add("ifgt 3 9 27");
        temp.add("ifgt a number here");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the executeCritter method when the code list has invalid critter instructions
    @Test
    public void invalidCode() {
        Interpreter interpreter = new Interpreter();
        List temp = new ArrayList<String>();
        temp.add("hopp");
        temp.add("g");
        temp.add("The primary focus of this text is problem-solving techniques that allow the construction of sophisticated, time-efficient programs. Nearly all of the material discussed is applicable in any programming language. Some would argue that a broad pseudocode description of these techniques could suffice to demonstrate concepts. However, we believe that working with live code is vitally important.");
        CritterImplementation critter = new CritterImplementation(temp);
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the loadSpecies method when an invalid file path is fed in
    @Test
    public void fileReadingTest() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadNullOutput = interpreter.loadSpecies(null);
        CritterSpecies loadGibberishOutput = interpreter.loadSpecies("aeiufnoisdbgpiqeurg");
        // hopefully you don't happen to have a path like this
        CritterSpecies loadInvalidPathOutput = interpreter.loadSpecies("random/path/specified");
    }

    // Test on the provided Hop critter and check if the right Critter methods are called
    @Test
    public void hopTest() throws IOException {
        ArrayList<String> ans = new ArrayList<String>();
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Hop.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
            ans.add("hop");
        }
        assert(ans.equals(critter.getExecutedCode()));
    }

    // Test on the provided Rover critter (without randomness) and check if the right Critter methods are called
    @Test
    public void nonRandomRoverTest() throws IOException {
        ArrayList<String> ans = new ArrayList<String>();
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/NonRandomRover.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        // run 5 steps of the simulation
        for (int i = 0; i < 5; i++) {
            interpreter.executeCritter(critter);
        }
        ans.add("getCellContent");
        ans.add("getCellContent");
        ans.add("hop");
        ans.add("getCellContent");
        ans.add("getCellContent");
        assert(ans.equals(critter.getExecutedCode()));
    }

    // Test on an input file with empty code
    @Test
    public void empty() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Empty.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test on an input file with gibberish text
    @Test
    public void gibberishFileTest() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Gibberish.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test the upper bound of the for loop condition in the executeCritter method
    @Test
    public void loopEdgeTest() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Rover.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        critter.setNextCodeLine(critter.getCode().size());
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

    // Test on an invalid go instruction (jump to a nonexistent line)
    @Test
    public void invalidGo() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/InvalidGo.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        // run 10 steps of the simulation
        for (int i = 0; i < 10; i++) {
            interpreter.executeCritter(critter);
        }
    }

}