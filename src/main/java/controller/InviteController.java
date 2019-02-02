package controller;

import com.google.gson.JsonElement;
import dao.InviteDao;
import model.Invite;
import payload.InvitePayload;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static etc.Response.fail;
import static etc.Response.getValidationMessages;
import static etc.Response.success;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class InviteController extends Controller {
    private InviteDao inviteDao = new InviteDao();

    public String create(Request req, Response res) {
        JsonElement json = gson.fromJson(req.body(), JsonElement.class);

        if (json.isJsonArray()) {
            // Bulk invite creation
            InvitePayload[] payload = gson.fromJson(req.body(), InvitePayload[].class);

            boolean issue = of(payload).anyMatch(i -> validator.validate(i).size() > 0);

            if (issue) {
                return fail(gson.toJsonTree("One or more of the invites are invalid."));
            }

            List<Invite> invites = of(payload).map(InvitePayload::toModel).collect(toList());
            inviteDao.createInvites(invites);

            return success(null);
        } else {
            // Single invite creation
            InvitePayload payload = gson.fromJson(req.body(), InvitePayload.class);

            Set<ConstraintViolation<InvitePayload>> issues = validator.validate(payload);

            if (!issues.isEmpty()) {
                return fail(gson.toJsonTree(getValidationMessages(issues)));
            }

            inviteDao.createInvite(payload.toModel());
            return success(null);
        }
    }
}
