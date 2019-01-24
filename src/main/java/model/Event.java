package model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private int eventId;

    @Min(value = 0, message = "The event must have a host.")
    @Column(name="host_user_id")
    private int hostUserId = -1;

    @NotEmpty(message = "The event must have a title.")
    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @NotNull(message = "The event must have a start time.")
    @Column(name="start_time")
    private Date startTime;

    @Min(value = 0, message = "The event must have a location.")
    @Column(name="latitude")
    private float latitude = -1;

    @Min(value = 0, message = "The event must have a location.")
    @Column(name="longitude")
    private float longitude = -1;
}
