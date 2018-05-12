package hu.unideb.inf.warehouse.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


/**
 * A Package adatelérési objektumot megvalósító osztály.
 */
public class PackageDAO {

    /**
     * Az entitásokat kezelő entitás menedzser objektum.
     */
    private static final EntityManager entityManager = new EntityManagement().getEntityManager();

    /**
     * Az {@code PackageDAO} osztály loggerje.
     */
    private static final Logger logger = LoggerFactory.getLogger(PackageDAO.class);

    /**
     * Lista, amelyben {@code Package} objektumokat tárolunk.
     */
    private static ObservableList<Package> packages = FXCollections.observableArrayList();

    /**
     * A {@code packages} lista feltöltése az adatbázisban lévő csomagokkal.
     */
    public void loadPackages() {
       packages.clear();
       packages.addAll(entityManager.createQuery("SELECT Pack FROM Package Pack").getResultList());
       logger.info("A csomagok sikeresen betöltődtek az adatbázisból.");
    }

    /**
     * Új csomag mentése az adatbázisba.
     * @param pack az új csomag.
     */
    public void saveNewPackage(Package pack) {
        entityManager.getTransaction().begin();
        entityManager.persist(pack);
        entityManager.getTransaction().commit();
        logger.info("A {} azonosítójú csomag sikeresen el lett mentve az adatbázisban.", pack.getPackageId());
    }

    /**
     * A paraméterül adott csomagot törli az adatbázisból.
     * @param pack a törlendő csomag.
     */
    public void deletePackage(Package pack) {
        entityManager.getTransaction().begin();
        entityManager.remove(pack);
        entityManager.getTransaction().commit();
        logger.info("A {} azonosítójú csomag sikeresen törölve lett az adatbázisból.", pack.getPackageId());
    }

    /**
     * A paraméterül adott csomag státuszának megváltoztatása a paraméterül adott státuszra és a kiszállítás dátumának mentése az adatbázisba, amennyiben kiszállításra került a csomag.
     * @param pack a csomag, amelynek a státuszát módosítjuk.
     * @param status a státusz, amelyre módosítjuk a csomag státuszát.
     */
    public void modifyPackageStatus(Package pack, String status) {
        entityManager.getTransaction().begin();
        pack.setStatus(status);
        logger.info("A {} azonosítójú csomag státusza sikeresen megváltoztatva a következőre: {}.", pack.getPackageId(), status);
        if(status.equals("Kiszállítva")) {
            pack.setDeliveryDate(LocalDateTime.now());
            logger.info("A {} azonosítójú csomag sikeresen kézbesítve lett a következő időpontban: ", pack.getPackageId(), LocalDateTime.now());
        }
        entityManager.getTransaction().commit();
    }

    /**
     * A paraméterül adott csomag azonosító alapján vissza adja a keresett csomagot.
     * @param packageId a csomag azonosítója.
     * @return a keresett csomag.
     */
    public Package getPackageByPackageId(String packageId) {
        return packages
                .stream()
                .filter(pack -> pack.getPackageId().equals(packageId))
                .findFirst()
                .get();
    }

    /**
     * Vissza adja a paraméterül adott státúszú és típusú csomagok listáját.
     * @param status egy csomag státusz, a csomagok szűrésére.
     * @param type egy csomag típus, a csomagok szűrésére.
     * @return a státusz és típus szerint szűrt csomagok listája.
     */
    public ObservableList<Package> getFilteredPackages(String status, String type) {
        if(status.equals("Mindegyik") && type.equals("Mindegyik")) {
            return packages;
        }
        else if(status.equals("Mindegyik")) {
            return packages
                    .stream()
                    .filter(pack -> pack.getPackageType().equals(type))
                    .collect(Collectors.collectingAndThen(toList(), FXCollections::observableArrayList));
        }
        else if(type.equals("Mindegyik")) {
            return packages
                    .stream()
                    .filter(pack -> pack.getStatus().equals(status))
                    .collect(Collectors.collectingAndThen(toList(), FXCollections::observableArrayList));
        }
        else {
            return packages
                    .stream()
                    .filter(pack -> pack.getStatus().equals(status) && pack.getPackageType().equals(type))
                    .collect(Collectors.collectingAndThen(toList(), FXCollections::observableArrayList));
        }
    }

    /**
     * Vissza ad egy listát a csomagok azonosítóival.
     * @return egy lista a csomagok azonosítóival.
     */
    public List<String> getPackageIds() {
        return packages
                .stream()
                .map(hu.unideb.inf.warehouse.models.Package::getPackageId)
                .collect(Collectors.toList());
    }

    /**
     * Vissza adja a csomagok listáját.
     * @return a csomagok listája.
     */
    public ObservableList<Package> getPackages() {
        return packages;
    }

    /**
     * Beállítja a csomagok listáját.
     * @param packages egy csomag lista.
     */
    public void setPackages(ObservableList<Package> packages) {
        this.packages = packages;
    }

}
