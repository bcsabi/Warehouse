package hu.unideb.inf.warehouse.models;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A csomagokat reprezentáló osztály, amely a csomag objektumot hozza létre.
 */
@Entity
@Table(name = "Package")
public class Package {

    /**
     * Az adatbázisban lévő azonosító, az elsődleges kulcs.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    /**
     * A csomag azonosítója.
     */
    @Column(name = "package_id")
    private String packageId;

    /**
     * A feladó neve.
     */
    @Column(name = "sender_name")
    private String senderName;

    /**
     * A feladó lakcíme.
     */
    @Column(name = "sender_home_address")
    private String senderHomeAddress;

    /**
     * A címzett neve.
     */
    @Column(name = "recipient_name")
    private String recipientName;

    /**
     * A szállítási cím.
     */
    @Column(name = "delivery_address")
    private String deliveryAddress;

    /**
     * A csomag feladásának dátuma.
     */
    @Column(name = "date_of_dispatch")
    private LocalDateTime dateOfDispatch;

    /**
     * A csomag típusa.
     */
    @Column(name = "package_type")
    private String packageType;

    /**
     * A csomag súlya.
     */
    @Column(name = "weight")
    private double weight;

    /**
     * A csomag státusza.
     */
    @Column(name = "status")
    private String status;

    /**
     * A csomag várható szállítási dátuma.
     */
    @Column(name = "excepted_delivery_date")
    private LocalDateTime exceptedDeliveryDate;

    /**
     * A kiszállítás dátuma.
     */
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    /**
     * A {@code Package} osztály paraméter nélküli konstruktora.
     */
    public Package() {

    }

    /**
     * A {@code Package} osztály konstruktora.
     *
     * @param packageId a csomag azonosítója.
     * @param senderName a feladó neve.
     * @param senderHomeAddress a feladó lakcíme.
     * @param recipientName a címzett neve.
     * @param deliveryAddress a szállítási cím.
     * @param dateOfDispatch a csomag feladásának dátuma.
     * @param packageType a csomag típusa.
     * @param weight a csomag súlya.
     * @param exceptedDeliveryDate várható szállítási idő.
     */
    public Package(String packageId, String senderName, String senderHomeAddress, String recipientName,
                   String deliveryAddress, LocalDateTime dateOfDispatch, String packageType, double weight,
                   LocalDateTime exceptedDeliveryDate) {
        this.packageId = packageId;
        this.senderName = senderName;
        this.senderHomeAddress = senderHomeAddress;
        this.recipientName = recipientName;
        this.deliveryAddress = deliveryAddress;
        this.dateOfDispatch = dateOfDispatch;
        this.packageType = packageType;
        this.weight = weight;
        this.status = "Feldolgozás alatt";
        this.exceptedDeliveryDate = exceptedDeliveryDate;
        this.deliveryDate = null;
    }

    /**
     * Visszaadja a csomag adatbázisbeli azonosítóját.
     * @return a csomag adatbázisbeli azonosítója.
     */
    public int getId() {
        return id;
    }

    /**
     * Beállítja a csomag adatbázisbeli azonosítóját.
     * @param id az adatbázisbeli azonosító.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Visszaadja a csomag azonosítóját.
     * @return a csomag azonosítója.
     */
    public String getPackageId() {
        return packageId;
    }

    /**
     * Beállítja a csomag azonosítóját.
     * @param packageId a csomag azonosítója.
     */
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    /**
     * Visszaadja a feladó nevét.
     * @return a feladó neve.
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Beállítja a feladó nevét.
     * @param senderName a feladó neve.
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * Visszaadja a feladó lakcímét.
     * @return a feladó lakcíme.
     */
    public String getSenderHomeAddress() {
        return senderHomeAddress;
    }

    /**
     * Beállítja a feladó lakcímét.
     * @param senderHomeAddress a feladó lakcíme.
     */
    public void setSenderHomeAddress(java.lang.String senderHomeAddress) {
        this.senderHomeAddress = senderHomeAddress;
    }

    /**
     * Visszaadja a címzett nevét.
     * @return a címzett neve.
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * Beállítja a címzett nevét.
     * @param recipientName a címzett neve.
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    /**
     * Visszaadja a szállítási címet.
     * @return a szállítási cím.
     */
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Beállítja a szállítási címet.
     * @param deliveryAddress a szállítási cím.
     */
    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * Visszaadja a csomag feladásának a dátumát.
     * @return a csomag feladásának a dátuma.
     */
    public LocalDateTime getDateOfDispatch() {
        return dateOfDispatch;
    }

    /**
     * Beállítja a csomag feladásának a dátumát.
     * @param dateOfDispatch a csomag feladásának a dátuma.
     */
    public void setDateOfDispatch(LocalDateTime dateOfDispatch) {
        this.dateOfDispatch = dateOfDispatch;
    }

    /**
     * Visszaadja a csomag típusát.
     * @return a csomag típusa.
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Beállítja a csomag típusát.
     * @param packageType a csomag típusa.
     */
    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    /**
     * Visszaadja a csomag súlyát.
     * @return a csomag súlya.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Beállítja a csomag súlyát.
     * @param weight a csomag súlya.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /***
     * Visszaadja a csomag státuszát.
     * @return a csomag státusza.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Beállítja a csomag státuszát.
     * @param status a csomag státusza.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Visszaadja a várható szállítás dátumát.
     * @return a várható szállítás dátuma.
     */
    public LocalDateTime getExceptedDeliveryDate() {
        return exceptedDeliveryDate;
    }

    /**
     * Beállítja a várható szállítás dátumát.
     * @param exceptedDeliveryDate a várható szállítás dátuma.
     */
    public void setExceptedDeliveryDate(LocalDateTime exceptedDeliveryDate) {
        this.exceptedDeliveryDate = exceptedDeliveryDate;
    }

    /**
     * Visszaadja a kiszállítás dátumát.
     * @return a kiszállítás dátuma.
     */
    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Beállítja a kiszállítás dátumát.
     * @param deliveryDate a kiszállítás dátuma.
     */
    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
