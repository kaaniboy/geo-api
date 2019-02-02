package dao;

import lombok.Cleanup;
import model.Event;

import javax.persistence.EntityManager;
import java.util.List;

import static etc.Helpers.createEntityManager;

public class EventDao extends Dao {
    public List<Event> getEvents() {
        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        List<Event> events = em.createQuery( "from Event", Event.class ).getResultList();
        em.getTransaction().commit();

        return events;
    }

    public void createEvent(Event event) {
        event.setEventId(0);

        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }

    public Event readEvent(int id) {
        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        Event event = em.find(Event.class, id);
        em.getTransaction().commit();

        return event;
    }

    public Event updateEvent(int id, Event updatedEvent) {
        Event existingEvent = readEvent(id);

        if (existingEvent == null) return null;

        if (updatedEvent.getTitle() != null) {
            existingEvent.setTitle(updatedEvent.getTitle());
        }

        if (updatedEvent.getDescription() != null) {
            existingEvent.setDescription(updatedEvent.getDescription());
        }

        if (updatedEvent.getStartTime() != null) {
            existingEvent.setStartTime(updatedEvent.getStartTime());
        }

        if (updatedEvent.getLatitude() >= 0) {
            existingEvent.setLatitude(updatedEvent.getLatitude());
        }

        if (updatedEvent.getLongitude() >= 0) {
            existingEvent.setLongitude(updatedEvent.getLongitude());
        }

        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        existingEvent = em.merge(existingEvent);
        em.getTransaction().commit();

        return existingEvent;
    }
}
