package hu.unideb.inf.warehouse.models;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

/**
 * Az {@code EntityManagement} osztály reprezentálja az adatbázissal való kapcsolat vezérlését.
 */
public class EntityManagement {

    /**
     * Az {@code EntityManagerFactory} objektum.
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Az entitásokat kezelő entitás menedzser objektum.
     */
    private static EntityManager entityManager;

    /**
     * Létrehozza az adatbázissal való kapcsolatot, a {@code entitiyManager} létrehozásával.
     * @param persistenceUnitName a persistence.xml megegtalálásához szükséges név.
     * @param properties a felhasználónév és jelszót tartalmazó properti - value kulcs értékpárok.
     */
    public void createConnection(String persistenceUnitName, Map<String,String> properties) {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Bezárja az adatbázissal való kapcsolatot.
     */
    public void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        entityManagerFactory = entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(javax.persistence.EntityManager entityManager) {
        entityManager = entityManager;
    }
}
