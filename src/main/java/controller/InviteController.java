package controller;

import dao.InviteDao;
import model.Invite;
import spark.Request;
import spark.Response;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static response.Response.fail;
import static response.Response.getValidationMessages;
import static response.Response.success;

public class InviteController extends BaseController {
    private InviteDao inviteDao = new InviteDao();

    public String create(Request req, Response res) {
        Invite invite = gson.fromJson(req.body(), Invite.class);

        System.out.println(invite);

        Set<ConstraintViolation<Invite>> issues = validator.validate(invite);

        if (issues.isEmpty()) {
            inviteDao.createInvite(invite);
            return success(null);
        } else {
            return fail(gson.toJsonTree(getValidationMessages(issues)));
        }
    }
}
