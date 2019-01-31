package controller;

import dao.InviteDao;
import payload.InvitePayload;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static etc.Response.fail;
import static etc.Response.getValidationMessages;
import static etc.Response.success;

public class InviteController extends BaseController {
    private InviteDao inviteDao = new InviteDao();

    public String create(Request req, Response res) {
        InvitePayload payload = gson.fromJson(req.body(), InvitePayload.class);

        Set<ConstraintViolation<InvitePayload>> issues = validator.validate(payload);

        if (issues.isEmpty()) {
            inviteDao.createInvite(payload.toModel());
            return success(null);
        } else {
            return fail(gson.toJsonTree(getValidationMessages(issues)));
        }
    }
}
