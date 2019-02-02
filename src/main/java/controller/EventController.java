package controller;

import com.google.gson.JsonObject;
import dao.EventDao;
import model.Event;
import payload.EventPayload;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;

import java.util.Set;

import static etc.Response.getValidationMessages;
import static etc.Response.success;
import static etc.Response.fail;

public class EventController extends Controller {
    private EventDao eventDao = new EventDao();

    public String create(Request req, Response res) {
        EventPayload payload = gson.fromJson(req.body(), EventPayload.class);

        Set<ConstraintViolation<EventPayload>> issues = validator.validate(payload);

        if (issues.isEmpty()) {
            eventDao.createEvent(payload.toModel());
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
            eventJson.add("event", gson.toJsonTree(event.toPayload()));
            return success(gson.toJsonTree(eventJson));
        } else {
            return fail("An event with the provided id does not exist.");
        }
    }

    public String update(Request req, Response res) {
        int id = Integer.parseInt(req.params("id"));

        EventPayload payload = gson.fromJson(req.body(), EventPayload.class);

        Event updatedEvent = eventDao.updateEvent(id, payload.toModel());

        if (updatedEvent != null) {
            JsonObject eventJson = new JsonObject();
            eventJson.add("event", gson.toJsonTree(updatedEvent.toPayload()));
            return success(gson.toJsonTree(eventJson));
        } else {
            return fail("An event with the provided id does not exist.");
        }
    }
}
