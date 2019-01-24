import controller.EventController;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        port(9000);

        EventController event = new EventController();

        post("/event", event::create);
        get("/event/:id", event::read);
    }
}
