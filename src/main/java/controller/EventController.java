package controller;

import com.google.gson.JsonObject;
import dao.EventDao;
import model.Event;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;

import java.util.Set;

import static response.Response.getValidationMessages;
import static response.Response.success;
import static response.Response.fail;

public class EventController extends BaseController {
    private EventDao eventDao = new EventDao();

    public String create(Request req, Response res) {
        Event event = gson.fromJson(req.body(), Event.class);

        Set<ConstraintViolation<Event>> issues = validator.validate(event);

        if (issues.isEmpty()) {
            eventDao.createEvent(event);
            return success(null);
        } else {
            return fail(gson.toJsonTree(getValidationMessages(issues)));
        }
    }

    public String read(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));

        Event event = eventDao.readEvent(id);

        if (event != null) {
            JsonObject eventJson = new JsonObject();
            eventJson.add("event", gson.toJsonTree(event));
            return success(gson.toJsonTree(eventJson));
        } else {
            return fail("An event with the provided id does not exist.");
        }
    }

    public String update(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));

        Event event = gson.fromJson(req.body(), Event.class);

        event = eventDao.updateEvent(id, event);

        if (event != null) {
            JsonObject eventJson = new JsonObject();
            eventJson.add("event", gson.toJsonTree(event));
            return success(gson.toJsonTree(eventJson));
        } else {
            return fail("An event with the provided id does not exist.");
        }
    }
}
