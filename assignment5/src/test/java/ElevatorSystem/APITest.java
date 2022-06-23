package ElevatorSystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class APITest {
    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void GoToValidFloorTest() {
        // GIVEN
        int departure = 1, destination = 5;

        // WHEN
        Object json = testRestTemplate.getForObject("http://localhost:" + localServerPort +
                        "/elevator?departure=" + departure +"&destination=" + destination, String.class);

        // THEN
        assertEquals(json, "{\"departure\":" + departure + ",\"destination\":" + destination + "}");
    }

    @Test
    void InValidDepartureTest() {
        // GIVEN
        int departure = -1, destination = 5;
        String expectedOuput = "Departure floor is invalid.";

        // WHEN
        Object json = testRestTemplate.getForObject("http://localhost:" + localServerPort +
                "/elevator?departure=" + departure + "&destination=" + destination, String.class);

        // THEN
        assertEquals(json, expectedOuput);
    }

    @Test
    void InValidDestinationTest() {
        // GIVEN
        int departure = 1, destination = 1000;
        String expectedOuput = "Destination floor is invalid.";

        // WHEN
        Object json = testRestTemplate.getForObject("http://localhost:" + localServerPort +
                "/elevator?departure=" + departure +"&destination=" + destination, String.class);

        // THEN
        assertEquals(json, expectedOuput);
    }
}