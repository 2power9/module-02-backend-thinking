package ElevatorSystem;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestController {
    private LinkedBlockingQueue<Request> requests;

    public RequestController() {
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
