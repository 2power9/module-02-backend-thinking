package elevatorSystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static elevatorSystem.Direction.STAY;

@SpringBootApplication
@RestController
public class Main {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello World!!!";
    }
    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(Main.class, args);
//        System.out.println("Hello World!!");
        ArrayList<Request> requests = new ArrayList<Request>();
        requests.add(new Request(1, 3));
        requests.add(new Request(10, 6));
        requests.add(new Request(7, 9));

        Elevator elevator = new Elevator();
        while (!requests.isEmpty() || elevator.action() != STAY) {
            if (requests.size() > 0) {
                Request request = requests.get(0);
                elevator.getRequest(request.getFrom(), request.getTo());
                requests.remove(0);
            }
            elevator.goToNewFloor();
        }
    }
}