package model;

import lombok.Data;
import payload.EventPayload;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="events")
public class Event extends BaseModel<EventPayload> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private int eventId;

    @ManyToOne
    @JoinColumn(name="host_user_id")
    private User host;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="latitude")
    private float latitude = -1;

    @Column(name="longitude")
    private float longitude = -1;

    @Override
    public EventPayload toPayload() {
        EventPayload payload = new EventPayload();

        payload.setEventId(eventId);
        payload.setHostUserId(host.getUserId());
        payload.setTitle(title);
        payload.setDescription(description);
        payload.setStartTime(startTime);
        payload.setLatitude(latitude);
        payload.setLongitude(longitude);

        return payload;
    }
}