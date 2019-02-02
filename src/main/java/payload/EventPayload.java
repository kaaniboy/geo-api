package payload;

import lombok.Cleanup;
import lombok.Data;
import model.Event;
import model.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.Date;

import static etc.Helpers.createEntityManager;

@Data
@Entity
public class EventPayload extends Payload<Event> {
    private int eventId;

    @Min(value = 0, message = "The event must have a host.")
    private int hostUserId = -1;

    @NotEmpty(message = "The event must have a title.")
    private String title;

    @Column(name="description")
    private String description;

    @NotNull(message = "The event must have a start time.")
    private Date startTime;

    @Min(value = 0, message = "The event must have a location.")
    private float latitude = -1;

    @Min(value = 0, message = "The event must have a location.")
    private float longitude = -1;

    @Override
    public Event toModel() {
        @Cleanup EntityManager em = createEntityManager();

        Event event = new Event();

        event.setEventId(eventId);
        event.setHost(em.getReference(User.class, hostUserId));
        event.setTitle(title);
        event.setDescription(description);
        event.setStartTime(startTime);
        event.setLatitude(latitude);
        event.setLongitude(longitude);

        return event;
    }
}