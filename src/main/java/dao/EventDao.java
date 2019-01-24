package dao;

import lombok.Cleanup;
import models.Event;

import javax.persistence.EntityManager;
import java.util.List;

public class EventDao extends BaseDao {
    public List<Event> getEvents() {
        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        List<Event> events = em.createQuery( "from Event", Event.class ).getResultList();
        em.getTransaction().commit();

        return events;
    }

    public void addEvent(Event event) {
        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }
}
