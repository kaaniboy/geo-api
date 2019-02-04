import auth.AuthToken;
import controller.EventController;
import controller.InviteController;

import javax.persistence.PersistenceException;

import auth.Auth;

import static etc.Response.fail;
import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        port(9000);

        setupFilters();
        setupRoutes();
    }

    private static void setupFilters() {
        before((req, res) -> res.type("application/json"));

        before((req, res) -> {
            String jwt = req.headers("Authorization");
            AuthToken auth = Auth.parseToken(jwt);

            if (auth == null) {
                halt(fail("You must be authenticated to access the API."));
                return;
            }
        });

        exception(PersistenceException.class, (e, req, res) -> {
            System.out.println(e.getMessage());
            res.body(fail("An error occurred when performing the operation."));
        });
    }

    private static void setupRoutes() {
        EventController event = new EventController();
        InviteController invite = new InviteController();

        post("/event", event::create);
        get("/event/:id", event::read);
        put("/event/:id", event::update);

        post("/invite", invite::create);
    }
}
