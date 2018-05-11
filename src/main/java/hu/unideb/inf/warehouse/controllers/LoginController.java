package hu.unideb.inf.warehouse.controllers;

import hu.unideb.inf.warehouse.models.EntityManagement;
import hu.unideb.inf.warehouse.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Az adatbázisba való bejelentkezést reprezentáló osztály.
 */
public class LoginController {

    @FXML
    TextField userName;

    @FXML
    PasswordField password;

    /**
     * A {@code LoginController} osztály loggerje.
     */
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML
    private void login() {
        try {
            Map<String, String> properties = new HashMap<>();
            properties.put("javax.persistence.jdbc.user", userName.getText());
            properties.put("javax.persistence.jdbc.password", password.getText());
            new EntityManagement().createConnection("storage", properties);
            setStorageScreen();
        }
        catch(ServiceException e) {
            logger.error(e.toString());
            loginAlert();
        }
    }

    private void loginAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bejelentkezési hiba");
        alert.setHeaderText(null);
        alert.setContentText("Nem sikerült csatlakozni az adatbázishoz.");
        alert.show();
    }

    private void setStorageScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/warehouse.fxml"));
            Stage stage = new Main().getStage();
            stage.setScene(new Scene(root, 800, 600));
            stage.setY(stage.getY() - 50);
            stage.setX(stage.getX() - 100);
            stage.show();
            logger.info("A raktár képernyő sikeresen betöltődött.");
        }
        catch(Exception e) {
            logger.error(e.toString());
        }
    }

    @FXML
    private void exit() {
        new Main().getStage().close();
    }
}
