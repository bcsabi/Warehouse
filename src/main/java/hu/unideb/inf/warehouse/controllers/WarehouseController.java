package hu.unideb.inf.warehouse.controllers;

import hu.unideb.inf.warehouse.models.EntityManagement;
import hu.unideb.inf.warehouse.models.PackageDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import hu.unideb.inf.warehouse.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * A raktár vezérlését reprezentáló osztály.
 */
public class WarehouseController {

    /**
     * A {@code WarehouseController} osztály loggerje.
     */
    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    @FXML
    private void loadNewPackageScreen() {
        try {
            loadScreen("/fxml/newpackage.fxml");
            logger.info("Az új csomag képernyő sikeresen betöltődött.");
        }
        catch(Exception e) {
            logger.error(e.toString());
        }
    }

    @FXML
    private void loadPackageManagerScreen() {
        try {
            new PackageDAO().loadPackages();
            loadScreen("/fxml/packagemanager.fxml");
            logger.info("A csomagokat menedzselő képernyő sikeresen betöltődött.");
        }
        catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @FXML
    private void closeApplication() {
        try {
            ButtonType yesButton = new ButtonType("Igen");
            ButtonType cancelButton = new ButtonType("Mégsem");
            Optional<ButtonType> result = exitAlert(yesButton, cancelButton).showAndWait();
            if (result.get() == yesButton) {
                new EntityManagement().closeConnection();
                logger.info("Az adatbázissal való kapcsolat sikeresen befejeződött.");
                new Main().getStage().close();
            }
        }
        catch (Exception e) {
            logger.error(e.toString());
        }
    }

    private Alert exitAlert(ButtonType yesButton, ButtonType cancelButton) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Kilépés");
        alert.setHeaderText(null);
        alert.setContentText("Biztosan ki szeretne lépni az applikációból?");
        alert.getButtonTypes().setAll(yesButton, cancelButton);
        return alert;
    }

    private void loadScreen(String fxml) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Main().getStage();
        stage.setScene(new Scene(root));
    }
}
