package ElevatorSystem;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

import static ElevatorSystem.Direction.*;

@Component
public class Elevator {
    int numFloors;
    int currentFloor;
    LinkedBlockingQueue[][] departure;
    int[] destination;
    Direction direction;

    Elevator() {
        direction = UP;
        numFloors = 10;
        currentFloor = 1;

        departure = new LinkedBlockingQueue[numFloors + 1][2];
        for (int floor = 0; floor <= numFloors; ++floor) {
            departure[floor][UP.getValue()] = new LinkedBlockingQueue<Integer>();
            departure[floor][DOWN.getValue()] = new LinkedBlockingQueue<Integer>();
        }

        destination = new int[numFloors + 1];
        Arrays.fill(destination, 0);
    }

    void getRequest(Request request) throws InterruptedException {
        if (request.getFrom() < request.getTo()) {
            departure[request.getFrom()][UP.getValue()].put(request.getTo());
        } else {
            departure[request.getFrom()][DOWN.getValue()].put(request.getTo());
        }
    }

    public void processPassengers() throws InterruptedException {
        if (destination[currentFloor] > 0) {
            System.out.println("[FLOOR " + currentFloor + "] Passenger(s) check-out.");
            destination[currentFloor] = 0;
        }
        LinkedBlockingQueue departureFloors = departure[currentFloor][direction.getValue()];

        if (!departureFloors.isEmpty()) {
            System.out.println("[FLOOR " + currentFloor + "] Passenger(s) check-in.");

        }

        while (!departureFloors.isEmpty()) {
            ++destination[(int) departureFloors.take()];
        }
    }

    public void goToNewFloor() {
        try {
            processPassengers();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (action() == STAY) return;

        System.out.println("[FLOOR STATUS] from " + currentFloor + " to " +
                (currentFloor + (direction == UP ? 1 : -1)));

        if (direction == UP) {
            ++currentFloor;
            if (currentFloor == numFloors) {
                direction = DOWN;
            }
        } else {
            --currentFloor;
            if (currentFloor == 1) {
                direction = UP;
            }
        }
    }

    public boolean havePassengers(int floor) {
        return !departure[floor][UP.getValue()].isEmpty() || destination[floor] > 0 ||
                !departure[floor][DOWN.getValue()].isEmpty();
    }

    public boolean keepMoving(Direction dir) {
        int floor = currentFloor;
        while (floor > 0 && floor <= numFloors) {
            if (havePassengers(floor)) return true;
            if (dir == UP) {
                ++floor;
            } else {
                --floor;
            }
        }
        return false;
    }
    public Direction action() {
        if (keepMoving(direction)) {
            return direction;
        }
        // switch direction
        if (keepMoving(direction == UP ? DOWN : UP)) {
            direction = (direction == UP ? DOWN : UP);
            return direction;
        }

        return STAY;
    }
}
