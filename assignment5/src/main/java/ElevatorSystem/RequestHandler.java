package ElevatorSystem;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RequestHandler {
    private LinkedBlockingQueue<Request> requests;

    public RequestHandler() {
        requests = new LinkedBlockingQueue<>();
    }

    void addRequest(Request request) throws InterruptedException {
        requests.put(request);
    }

    Request takeRequest() throws InterruptedException {
        return requests.take();
    }

    boolean isEmpty() {
        return requests.isEmpty();
    }
}
