package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseDao {
    private EntityManagerFactory factory;

    public BaseDao() {
        factory = Persistence.createEntityManagerFactory("Main");
    }

    protected EntityManager createEntityManager() {
        return factory.createEntityManager();
    }
}
