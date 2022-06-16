package ElevatorSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static ElevatorSystem.Direction.STAY;

@RestController
public class ElevatorController {
    RequestController controller;
    @Autowired
    Elevator elevator;

    public ElevatorController() {
        controller = new RequestController();
        elevator = new Elevator();

        Thread runThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[ELEVATOR STATUS] Running");

                while (true) {
                    if (!controller.isEmpty()) {
                        try {
                            Request request = controller.takeRequest();
                            elevator.getRequest(request);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

//                    if (elevator.action() != STAY) {
                        elevator.goToNewFloor();
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
//                    }
                }
            }
        });

        runThread.start();
    }

    @GetMapping("/elevator")
    public ResponseEntity greeting(@RequestParam(value = "departure", defaultValue = "1") int departure,
                                   @RequestParam(value = "destination", defaultValue = "1") int destination)
                                    throws InterruptedException {

        controller.addRequest(new Request(departure, destination));

        return ResponseEntity.status(HttpStatus.OK).body(new Response(departure, destination));
    }
}
