package hu.unideb.inf.storage;

import hu.unideb.inf.warehouse.models.Package;
import hu.unideb.inf.warehouse.models.PackageDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class PackageDAOTest {

    private final PackageDAO packageDAO = new PackageDAO();

    private Package testPackage1 = new Package("SZ000000000", "S1", "H1","R1", "D1", LocalDateTime.now(), "Szabványos", 20, LocalDateTime.now().plusDays(1));
    private Package testPackage2 = new Package("SZ111111111", "S2", "H2","R2", "D2", LocalDateTime.now(), "Szabványos", 50, LocalDateTime.now().plusDays(2));
    private Package testPackage3 = new Package("SZ222222222", "S3", "H3","R3", "D3", LocalDateTime.now(), "Szabványos", 10, LocalDateTime.now().plusDays(1));
    private Package testPackage4 = new Package("T000000000", "S4", "H4","R4", "D4", LocalDateTime.now(), "Törékeny", 15, LocalDateTime.now().plusDays(2));
    private Package testPackage5 = new Package("T111111111", "S5", "H5","R5", "D5", LocalDateTime.now(), "Törékeny", 10, LocalDateTime.now().plusDays(2));
    private Package testPackage6 = new Package("T222222222", "S6", "H6","R6", "D6", LocalDateTime.now(), "Törékeny", 50, LocalDateTime.now().plusDays(3));
    private Package testPackage7 = new Package("V000000000", "S7", "H7","R7", "D7", LocalDateTime.now(), "Veszélyes", 25, LocalDateTime.now().plusDays(3));
    private Package testPackage8 = new Package("V111111111", "S8", "H8","R8", "D8", LocalDateTime.now(), "Veszélyes", 50, LocalDateTime.now().plusDays(4));
    private Package testPackage9 = new Package("V222222222", "S9", "H9","R9", "D9", LocalDateTime.now(), "Veszélyes", 15, LocalDateTime.now().plusDays(3));

    private static ObservableList<Package> testPackages = FXCollections.observableArrayList();

    @Before
    public void setTestPackages() {
        testPackages.addAll(testPackage1, testPackage2, testPackage3, testPackage4, testPackage5, testPackage6,testPackage7, testPackage8, testPackage9);
        testPackage2.setStatus("Szállítás alatt");
        testPackage5.setStatus("Szállítás alatt");
        testPackage8.setStatus("Szállítás alatt");
        testPackage3.setStatus("Kiszállítva");
        testPackage6.setStatus("Kiszállítva");
        testPackage9.setStatus("Kiszállítva");
    }

    @Test
    public void setPackagesTest() {
        packageDAO.setPackages(testPackages);
        Assert.assertEquals(testPackages, packageDAO.getPackages());
    }

    @Test
    public void getPackageByPackageIdTest() {
        Package pack = packageDAO.getPackageByPackageId("SZ000000000");
        Assert.assertEquals(testPackage1, pack);
        pack = packageDAO.getPackageByPackageId("SZ111111111");
        Assert.assertEquals(testPackage2, pack);
        pack = packageDAO.getPackageByPackageId("SZ222222222");
        Assert.assertEquals(testPackage3, pack);
        pack = packageDAO.getPackageByPackageId("T000000000");
        Assert.assertEquals(testPackage4, pack);
        pack = packageDAO.getPackageByPackageId("T111111111");
        Assert.assertEquals(testPackage5, pack);
        pack = packageDAO.getPackageByPackageId("T222222222");
        Assert.assertEquals(testPackage6, pack);
        pack = packageDAO.getPackageByPackageId("V000000000");
        Assert.assertEquals(testPackage7, pack);
        pack = packageDAO.getPackageByPackageId("V111111111");
        Assert.assertEquals(testPackage8, pack);
        pack = packageDAO.getPackageByPackageId("V222222222");
        Assert.assertEquals(testPackage9, pack);
    }

    @Test
    public void getFilteredPackagesTest() {
        ObservableList<Package> exceptedPackages;
        ObservableList<Package> actualPackages = packageDAO.getFilteredPackages("Mindegyik", "Mindegyik");
        Assert.assertEquals(testPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Mindegyik", "Feldolgozás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage1, testPackage4, testPackage7);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Mindegyik", "Szállítás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage2, testPackage5, testPackage8);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Mindegyik", "Kiszállítva");
        exceptedPackages = FXCollections.observableArrayList(testPackage3, testPackage6, testPackage9);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Szabványos", "Mindegyik");
        exceptedPackages = FXCollections.observableArrayList(testPackage1, testPackage2, testPackage3);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Szabványos", "Feldolgozás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage1);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Szabványos", "Szállítás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage2);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Szabványos", "Kiszállítva");
        exceptedPackages = FXCollections.observableArrayList(testPackage3);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Törékeny", "Mindegyik");
        exceptedPackages = FXCollections.observableArrayList(testPackage4, testPackage5, testPackage6);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Törékeny", "Feldolgozás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage4);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Törékeny", "Szállítás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage5);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Törékeny", "Kiszállítva");
        exceptedPackages = FXCollections.observableArrayList(testPackage6);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Veszélyes", "Mindegyik");
        exceptedPackages = FXCollections.observableArrayList(testPackage3, testPackage6, testPackage9);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Veszélyes", "Feldolgozás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage3);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Veszélyes", "Szállítás alatt");
        exceptedPackages = FXCollections.observableArrayList(testPackage6);
        Assert.assertEquals(exceptedPackages, actualPackages);
        actualPackages = packageDAO.getFilteredPackages("Veszélyes", "Kiszállítva");
        exceptedPackages = FXCollections.observableArrayList(testPackage9);
        Assert.assertEquals(exceptedPackages, actualPackages);
    }

    @Test
    public void getPackageIdsTest() {
        List<String> exceptedPackageIds = Arrays.asList(
                "SZ000000000", "SZ111111111", "SZ222222222",
                "T000000000", "T111111111", "T222222222",
                "V000000000", "V111111111", "V222222222"
        );
        Assert.assertEquals(exceptedPackageIds, packageDAO.getPackageIds());
    }

}
