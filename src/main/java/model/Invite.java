package model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@Entity
@Table(name="invites")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invite_id")
    private int inviteId;

    @Column(name="event_id")
    @Min(value = 0, message = "The invite must be tied to an event.")
    private int eventId = -1;

    @Column(name="inviter_id")
    @Min(value = 0, message = "The invite must have an inviter.")
    private int inviterId = -1;

    @Column(name="guest_id")
    @Min(value = 0, message = "The invite must have a guest.")
    private int guestId = -1;

    @Column(name="state")
    private String state = "pending";
}
