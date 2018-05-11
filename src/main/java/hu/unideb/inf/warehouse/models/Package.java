package hu.unideb.inf.warehouse.models;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A csomagokat reprezentáló osztály, amely a csomag objektumot hozza létre.
 */
@Entity
@Table(name = "Package")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "package_id")
    private String packageId;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "sender_home_address")
    private String senderHomeAddress;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "date_of_dispatch")
    private LocalDateTime dateOfDispatch;

    @Column(name = "package_type")
    private String packageType;

    @Column(name = "weight")
    private double weight;

    @Column(name = "status")
    private String status;

    @Column(name = "excepted_delivery_date")
    private LocalDateTime exceptedDeliveryDate;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderHomeAddress() {
        return senderHomeAddress;
    }

    public void setSenderHomeAddress(java.lang.String senderHomeAddress) {
        this.senderHomeAddress = senderHomeAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getDateOfDispatch() {
        return dateOfDispatch;
    }

    public void setDateOfDispatch(LocalDateTime dateOfDispatch) {
        this.dateOfDispatch = dateOfDispatch;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getExceptedDeliveryDate() {
        return exceptedDeliveryDate;
    }

    public void setExceptedDeliveryDate(LocalDateTime exceptedDeliveryDate) {
        this.exceptedDeliveryDate = exceptedDeliveryDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
