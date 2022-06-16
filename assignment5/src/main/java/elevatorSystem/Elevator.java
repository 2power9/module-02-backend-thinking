package elevatorSystem;

import java.util.ArrayList;
import java.util.Arrays;

import static elevatorSystem.Direction.*;

public class Elevator {
    int numFloors;
    int currentFloor;
    ArrayList[][] departure;
    int[] destination;
    Direction direction;

    Elevator() {
        direction = UP;
        numFloors = 10;
        currentFloor = 1;

        departure = new ArrayList[numFloors + 1][2];
        for (int floor = 0; floor <= numFloors; ++floor) {
            departure[floor][UP.getValue()] = new ArrayList<Integer>();
            departure[floor][DOWN.getValue()] = new ArrayList<Integer>();
        }

        destination = new int[numFloors + 1];
        Arrays.fill(destination, 0);
    }

    void getRequest(int from, int to) {
        departure[from][(from < to ? UP : DOWN).getValue()].add(to);
    }

    public void getPassengers() {
        destination[currentFloor] = 0;
        ArrayList destinationFloors = departure[currentFloor][direction.getValue()];
        while (!destinationFloors.isEmpty()) {
            ++destination[(int) destinationFloors.get(0)];
            destinationFloors.remove(0);
        }
    }

    public void goToNewFloor() {
        getPassengers();
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
        return departure[floor][UP.getValue()].size() > 0 || destination[floor] > 0 ||
                departure[floor][DOWN.getValue()].size() > 0;
    }

    public boolean keepMoving(Direction dir) {
        int floor = currentFloor;
        while (floor > 0 && floor <= numFloors) {
            if (havePassengers(floor)) return true;
            if (direction == UP) {
                ++floor;
            } else {
                --floor;
            }
        }
        return false;
    }
    public Direction action() {
        if (keepMoving(direction)) return direction;

        // switch direction
        if (keepMoving(direction == UP ? DOWN : UP)) {
            direction = (direction == UP ? DOWN : UP);
            return direction;
        }

        return STAY;
    }
}
