package hu.unideb.inf.warehouse.controllers;

import hu.unideb.inf.warehouse.Main;
import hu.unideb.inf.warehouse.models.Package;
import hu.unideb.inf.warehouse.models.PackageDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * A csomagok kezelését reprezentáló osztály.
 */
public class PackageManagerController {

    @FXML
    private ComboBox statuses;

    @FXML
    private ComboBox types;

    @FXML
    private TableView<Package> packageTable;

    @FXML
    private TableColumn<Package, String> packageIds;

    @FXML
    private Label packageId;

    @FXML
    private Label senderName;

    @FXML
    private Label senderHomeAddress;

    @FXML
    private Label recipientName;

    @FXML
    private Label deliveryAddress;

    @FXML
    private Label type;

    @FXML
    private Label weight;

    @FXML
    private Label status;

    @FXML
    private Label dateOfDispatch;

    @FXML
    private Label exceptedDeliveryDate;

    @FXML
    private Label deliveryDate;

    @FXML
    private Button modifyStatusButton;

    @FXML
    private Button deletePackageButton;

    /**
     * A {@code PackageDAO} objektum.
     */
    private final PackageDAO packageDAO = new PackageDAO();

    /**
     * A {@code PackageManagerController} osztály loggerje.
     */
    private static final Logger logger = LoggerFactory.getLogger(PackageManagerController.class);

    @FXML
    private void initialize() {
        loadPackages();
    }

    private void loadPackages() {
        try {
            packageTable.setItems(packageDAO.getPackages());
            if(packageTable.getItems().size() <= 0) {
                throw new NullPointerException();
            }
            packageIds.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPackageId()));
            setListener();
        }
        catch (NullPointerException e) {
            logger.error(e.toString());
            noPackageFoundAlert("A raktár tartalma üres.\nJelenleg még nem adtak le egyetlen csomagot sem.");
        }
    }

    @FXML
    private void filter() {
        if(!statuses.getSelectionModel().isEmpty() && !types.getSelectionModel().isEmpty()) {
            String status = statuses.getSelectionModel().getSelectedItem().toString(),
                     type = types.getSelectionModel().getSelectedItem().toString();
            try {
                packageTable.setItems(packageDAO.getFilteredPackages(status, type));
                if (packageTable.getItems().size() <= 0) {
                    throw new NullPointerException();
                }
                packageIds.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPackageId()));
                setListener();
            }
            catch(NullPointerException e) {
                logger.error(e.toString());
                noPackageFoundAlert("Nincs egyetlen egy ilyen kombinációjú csomag sem!");
            }
        }
    }

    @FXML
    private void modifyStatus() {
        Package pack = packageDAO.getPackageByPackageId(packageId.getText());
        switch(pack.getStatus()) {
            case "Feldolgozás alatt" :
                if(modifyStatusConfirmation("Szállítás alatt")) {
                    packageDAO.modifyPackageStatus(pack, "Szállítás alatt");
                    modifyStatusButton.setText("Kiszállítva");
                    status.setText("Szállítás alatt");
                }
                break;
            case "Szállítás alatt" :
                if(modifyStatusConfirmation("Kiszállítva")) {
                    packageDAO.modifyPackageStatus(pack, "Kiszállítva");
                    modifyStatusButton.setVisible(false);
                    deletePackageButton.setVisible(false);
                    status.setText("Kiszállítva");
                    break;
                }
        }
    }

    private Boolean modifyStatusConfirmation(String status) {
        ButtonType yesButton = new ButtonType("Igen");
        ButtonType cancelButton = new ButtonType("Mégsem");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Csomag státuszának módosítása");
        alert.setHeaderText(null);
        alert.setContentText("Biztosan meg szeretné változtaztatni a csomag státuszát a következőre: " + status + "?");
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        Optional<ButtonType> result = deleteAlert(yesButton, cancelButton).showAndWait();
        return result.get() == yesButton;
    }

    @FXML
    private void deletePackage() {
        Package pack = packageDAO.getPackageByPackageId(packageId.getText());
        ButtonType yesButton = new ButtonType("Igen");
        ButtonType cancelButton = new ButtonType("Mégsem");
        Optional<ButtonType> result = deleteAlert(yesButton, cancelButton).showAndWait();
        if(result.get() == yesButton) {
            packageDAO.deletePackage(pack);
        }
        clearLabels();
        loadPackages();
    }

    private Alert deleteAlert(ButtonType yesButton, ButtonType cancelButton) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Csomag törlése");
        alert.setHeaderText(null);
        alert.setContentText("Biztosan törölni szeretné a " + packageId.getText() + " azonosítójú csomagot?");
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        return alert;
    }

    private void setListener() {
        packageTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showPackageDetails(newValue));
    }

    private void noPackageFoundAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nem található csomag");
        alert.setHeaderText(null);
        alert.setContentText(alertText);
        alert.showAndWait();
    }

    private void showPackageDetails(Package pack) {
        if(pack != null) {
            setPackageDetails(pack);
        }
        else {
            clearLabels();
        }
    }

    private void setPackageDetails(Package pack) {
        packageId.setText(pack.getPackageId());
        senderName.setText(pack.getSenderName());
        senderHomeAddress.setText(pack.getSenderHomeAddress());
        recipientName.setText(pack.getRecipientName());
        deliveryAddress.setText(pack.getDeliveryAddress());
        type.setText(pack.getPackageType());
        weight.setText(String.valueOf(pack.getWeight()) + " kg");
        status.setText(pack.getStatus());
        dateOfDispatch.setText(pack.getDateOfDispatch().toString());
        exceptedDeliveryDate.setText(pack.getExceptedDeliveryDate().toString());
        switch(pack.getStatus()) {
            case "Feldolgozás alatt" :
                modifyStatusButton.setText("Szállítás alatt");
                modifyStatusButton.setVisible(true);
                deletePackageButton.setVisible(true);
                deliveryDate.setText("");
                break;
            case "Szállítás alatt" :
                modifyStatusButton.setText("Kiszállítva");
                modifyStatusButton.setVisible(true);
                deletePackageButton.setVisible(true);
                deliveryDate.setText("");
                break;
            case "Kiszállítva" :
                modifyStatusButton.setVisible(false);
                deletePackageButton.setVisible(false);
                deliveryDate.setText(pack.getDeliveryDate().toString());
                break;
        }
        logger.info("A csomag adatok sikeresen betöltve.");
    }

    private void clearLabels() {
        packageId.setText("");
        senderName.setText("");
        senderHomeAddress.setText("");
        recipientName.setText("");
        deliveryAddress.setText("");
        type.setText("");
        weight.setText("");
        status.setText("");
        dateOfDispatch.setText("");
        exceptedDeliveryDate.setText("");
        deliveryDate.setText("");
        logger.info("Az összes label sikeresen megtisztítva.");
    }

    @FXML
    private void backToWelcomeScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/warehouse.fxml"));
            Stage stage = new Main().getStage();
            stage.setScene(new Scene(root));
        }
        catch(Exception e) {
            logger.error(e.toString());
        }
    }
}
