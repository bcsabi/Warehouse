package hu.unideb.inf.warehouse.controllers;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.models.Package;
import hu.unideb.inf.warehouse.models.PackageDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * A csomagok leadását reprezentáló osztály.
 */
public class NewPackageController {

    @FXML
    private TextField senderName;

    @FXML
    private TextField senderHomeAddress;

    @FXML
    private TextField recipientName;

    @FXML
    private TextField deliveryAddress;

    @FXML
    private ComboBox packageType;

    @FXML
    private TextField weight;

    /**
     * A {@code NewPackageController} osztály loggerje.
     */
    private static final Logger logger = LoggerFactory.getLogger(NewPackageController.class);

    @FXML
    private void addNewPackageToStorage() {
        try {
            if(senderName.getText().isEmpty() || senderHomeAddress.getText().isEmpty() ||
                    recipientName.getText().isEmpty() || deliveryAddress.getText().isEmpty() ||
                    packageType.getSelectionModel().isEmpty() || weight.getText().isEmpty()) {
                throw new NullPointerException();
            }
            if(senderName.getText().matches(".*\\d+.*") || recipientName.getText().matches(".*\\d+.*")) {
                throw new IllegalArgumentException();
            }
            new PackageDAO().saveNewPackage(new Package(
                    generatePackageId(),
                    senderName.getText(),
                    senderHomeAddress.getText(),
                    recipientName.getText(),
                    deliveryAddress.getText(),
                    LocalDateTime.now(),
                    packageType.getSelectionModel().getSelectedItem().toString(),
                    Double.parseDouble(weight.getText()),
                    getExceptedDeliveryDate())
            );
            newPackageCreatedAlert();
            clearFields();
        }
        catch(NullPointerException e) {
            logger.error(e.toString());
            notEnoughDataAlert();
        }
        catch(NumberFormatException e) {
            logger.error(e.toString());
            illegalCharacterInTheWeightFieldAlert();
        }
        catch(IllegalArgumentException e) {
            logger.error(e.toString());
            illegalCharacterInTheNameFieldAlert();
        }
    }

    private LocalDateTime getExceptedDeliveryDate() {
        int plusDays = 1;
        if(Double.parseDouble(weight.getText()) >= 50) {
            plusDays++;
        }
        switch (packageType.getSelectionModel().getSelectedItem().toString()) {
            case "Törékeny": plusDays++; break;
            case "Veszélyes": plusDays += 2; break;
        }
        logger.info("A várható szállítási idő: ", LocalDateTime.now().plusDays(plusDays).toString());
        return LocalDateTime.now().plusDays(plusDays);
    }

    private String generatePackageId() {
        String packageId = "";
        switch (packageType.getSelectionModel().getSelectedItem().toString()) {
            case "Törékeny": packageId += "TÖ"; break;
            case "Veszélyes": packageId += "VE" ; break;
            default : packageId += "SZ"; break;
        }
        int randomNum = new Random().nextInt(899999999) + 100000000;
        List<String> transactionIds = new PackageDAO().getPackageIds();
        while (transactionIds.contains(String.valueOf(randomNum))) {
            randomNum = new Random().nextInt(899999999) + 100000000;
        }
        logger.info("Az új package azonosító generálása sikeres volt: {}", packageId + randomNum);
        return packageId + randomNum;
    }

    private void newPackageCreatedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Csomag leadás sikeres volt");
        alert.setHeaderText(null);
        alert.setContentText("A csomag várható kiszállítási ideje: " + getExceptedDeliveryDate());
        alert.show();
    }

    private void notEnoughDataAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nem elegendő adat");
        alert.setHeaderText(null);
        alert.setContentText("Nincs elegendő mező kitöltve!\nKérjük ellenőrizze, hogy mindent kitöltött-e!");
        alert.show();
    }

    private void illegalCharacterInTheNameFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("A megadott név helytelen");
        alert.setHeaderText(null);
        alert.setContentText("Név nem tartalmazhat számot!\nKérjük ellenőrizze, hogy megfelelően adta-e meg!");
        alert.show();
    }

    private void illegalCharacterInTheWeightFieldAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nem megfelelő adat a súly mezőben");
        alert.setHeaderText(null);
        alert.setContentText("A súly mező csak számot tartalmazhat, amelynek értéke kg-ban értendő!");
        alert.show();
    }

    private void clearFields() {
        senderName.clear();
        senderHomeAddress.clear();
        recipientName.clear();
        deliveryAddress.clear();
        packageType.getSelectionModel().clearSelection();
        weight.clear();
        logger.info("A fields sikeresen megtisztítva.");
    }

    @FXML
    private void backToWelcomeScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/warehouse.fxml"));
            new Main().getStage().setScene(new Scene(root));
        }
        catch(Exception e) {
            logger.error(e.toString());
        }
    }
}
