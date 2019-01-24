package controller;

import com.google.gson.Gson;
import dao.EventDao;
import models.Event;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static response.Response.getValidationMessages;
import static response.Response.success;
import static response.Response.fail;
import static response.GsonHelper.gson;

public class EventController extends BaseController {
    private EventDao eventDao = new EventDao();

    public String create(Request req, Response res) {
        res.type("application/json");

        Event event = gson().fromJson(req.body(), Event.class);
        System.out.println(event);

        Set<ConstraintViolation<Event>> issues = validator.validate(event);

        if (issues.isEmpty()) {
            eventDao.addEvent(event);
            return success(null);
        } else {
            return fail(gson().toJsonTree(getValidationMessages(issues)));
        }
    }

    public String read(Request req, Response res) {
        return "event/read";
    }
}
