package assignment;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CritterTest {

    @Test
    public void empty() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Empty.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        interpreter.executeCritter(critter);
    }

    @Test
    public void hopTest() throws IOException {
        Interpreter interpreter = new Interpreter();
        CritterSpecies loadOutput = interpreter.loadSpecies("species/Hop.cri");
        CritterImplementation critter = new CritterImplementation(loadOutput.getCode());
        interpreter.executeCritter(critter);
    }

}