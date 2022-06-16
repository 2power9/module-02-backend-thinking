package ElevatorSystem;

public class Response {
    int departure;
    int destination;

    public int getDeparture() {
        return departure;
    }

    public void setDeparture(int departure) {
        this.departure = departure;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }


    public Response() {
        departure = 1;
        destination = 1;
    }

    public Response(int departure, int destination) {
        this.departure = departure;
        this.destination = destination;
    }

}
