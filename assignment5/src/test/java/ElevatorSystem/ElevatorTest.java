package ElevatorSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static ElevatorSystem.Direction.STAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorTest {
    private PrintStream standardOut;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    public void setUp() {
        standardOut = System.out;
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void elevatorUpTest() throws InterruptedException {
        // GIVEN
        Elevator elevator = new Elevator();
        File file = new File(System.getProperty("user.dir")  + "/src/test/java/ElevatorSystem/expectedOutput/goUp.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String expectText = "";
        while (sc.hasNextLine()) {
            expectText += sc.nextLine() + "\n";
        }

        // WHEN
        elevator.getRequest(new Request(1, 5));
        while (elevator.action() != STAY) {
            elevator.goToNewFloor();
        }

        // THEN
        assertEquals(expectText.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void elevatorDownTest() throws InterruptedException {
        // GIVEN
        Elevator elevator = new Elevator();
        File file = new File(System.getProperty("user.dir")  + "/src/test/java/ElevatorSystem/expectedOutput/goDown.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String expectText = "";
        while (sc.hasNextLine()) {
            expectText += sc.nextLine() + "\n";
        }

        // WHEN
        elevator.getRequest(new Request(5, 1));
        while (elevator.action() != STAY) {
            elevator.goToNewFloor();
        }

        // THEN
        assertEquals(expectText.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void requestUpWhileElevatorDownTest() throws InterruptedException {
        // GIVEN
        Elevator elevator = new Elevator();
        File file = new File(System.getProperty("user.dir")  + "/src/test/java/ElevatorSystem/expectedOutput/requestUpWhileElevatorDown.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String expectText = "";
        while (sc.hasNextLine()) {
            expectText += sc.nextLine() + "\n";
        }

        // WHEN
        elevator.getRequest(new Request(5, 1));
        boolean addedRequest2to3 = false;
        while (elevator.action() != STAY) {
            elevator.goToNewFloor();
            if (elevator.getCurrentFloor() == 4 && !addedRequest2to3) {
                elevator.getRequest(new Request(2, 3));
            }
        }

        // THEN
        assertEquals(expectText.trim(), outputStreamCaptor.toString().trim());
    }

    @Test
    void requestDownWhileElevatorUpTest() throws InterruptedException {
        // GIVEN
        Elevator elevator = new Elevator();
        File file = new File(System.getProperty("user.dir")  + "/src/test/java/ElevatorSystem/expectedOutput/requestDownWhileElevatorUp.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String expectText = "";
        while (sc.hasNextLine()) {
            expectText += sc.nextLine() + "\n";
        }

        // WHEN
        elevator.getRequest(new Request(1, 5));
        boolean addedRequest3to1 = false;
        while (elevator.action() != STAY) {
            elevator.goToNewFloor();
            if (elevator.getCurrentFloor() == 3 && !addedRequest3to1) {
                elevator.getRequest(new Request(3, 1));
            }
        }

        // THEN
        assertEquals(expectText.trim(), outputStreamCaptor.toString().trim());
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);

    }
}
