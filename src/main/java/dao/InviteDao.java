package dao;

import lombok.Cleanup;
import model.Invite;

import javax.persistence.EntityManager;

public class InviteDao extends BaseDao {
    public void createInvite(Invite invite) {
        @Cleanup EntityManager em = createEntityManager();

        em.getTransaction().begin();
        em.persist(invite);
        em.getTransaction().commit();
    }
}
