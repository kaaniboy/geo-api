package model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="invites")
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invite_id")
    private int inviteId;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="inviter_id")
    private User inviter;

    @ManyToOne
    @JoinColumn(name="guest_id")
    private User guest;

    @Column(name="state")
    private String state = "pending";
}
