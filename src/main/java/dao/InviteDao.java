package dao;

import lombok.Cleanup;
import model.Invite;

import javax.persistence.EntityManager;
import java.util.List;

import static etc.Helpers.createEntityManager;

public class InviteDao extends Dao {
    public List<Invite> getInvites() {
        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        List<Invite> invites = em.createQuery( "from Invite", Invite.class ).getResultList();
        em.getTransaction().commit();

        return invites;
    }

    public void createInvite(Invite invite) {
        invite.setInviteId(0);

        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        em.persist(invite);
        em.getTransaction().commit();
    }

    public void createInvites(List<Invite> invites) {
        invites.forEach(i -> i.setInviteId(0));

        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        invites.forEach(em::persist);
        em.getTransaction().commit();
    }
}
