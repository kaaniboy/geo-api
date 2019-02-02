package payload;

import lombok.Cleanup;
import lombok.Data;
import model.Event;
import model.Invite;
import model.User;

import javax.persistence.*;
import javax.validation.constraints.Min;

import static etc.Helpers.createEntityManager;

@Data
@Entity
public class InvitePayload extends Payload<Invite> {
    private int inviteId;

    @Min(value = 0, message = "The invite must be tied to an event.")
    private int eventId = -1;

    @Min(value = 0, message = "The invite must have an inviter.")
    private int inviterId = -1;

    @Min(value = 0, message = "The invite must have a guest.")
    private int guestId = -1;

    private String state = "pending";

    @Override
    public Invite toModel() {
        @Cleanup EntityManager em = createEntityManager();

        Invite invite = new Invite();

        invite.setInviteId(inviteId);
        invite.setEvent(em.getReference(Event.class, eventId));
        invite.setInviter(em.getReference(User.class, inviterId));
        invite.setGuest(em.getReference(User.class, guestId));

        return invite;
    }
}