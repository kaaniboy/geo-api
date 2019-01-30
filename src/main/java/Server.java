import controller.EventController;
import controller.InviteController;

import javax.persistence.PersistenceException;

import static response.Response.fail;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        port(9000);

        before((req, res) -> res.type("application/json"));

        EventController event = new EventController();
        InviteController invite = new InviteController();

        post("/event", event::create);
        get("/event/:id", event::read);
        put("/event/:id", event::update);

        post("/invite", invite::create);

        exception(PersistenceException.class, (e, req, res) -> {
            res.body(fail("An error occurred when performing the operation."));
        });
    }
}
