package ElevatorSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@RestController
public class ElevatorController {
    @Autowired
    RequestHandler requestHandler;
    @Autowired
    Elevator elevator;

    @PostConstruct
    public void init() {
        Thread runThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[ELEVATOR STATUS] Running");

                while (true) {
                    if (!requestHandler.isEmpty()) {
                        try {
                            Request request = requestHandler.takeRequest();
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

        requestHandler.addRequest(new Request(departure, destination));

        return ResponseEntity.status(HttpStatus.OK).body(new Response(departure, destination));
    }
}
