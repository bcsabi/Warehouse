package hu.unideb.inf.warehouse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Az applikációt elindító osztály.
 */
public class Main extends Application {

    /**
     * A stage példányunk, amely mindig az aktuális képernyőt jeleníti meg.
     */
    private static Stage stage;

    /**
     * A {@code Main} osztály loggerje.
     */
    private final Logger logger =  LoggerFactory.getLogger(Main.class);

    /**
     * Minden JavaFX alkalmazás fő belépési pontja, amely az alkalmazás elindítását végzi el.
     *
     * @param primaryStage az elsődleges stage.
     */
    @Override
    public void start(Stage primaryStage){
        try {
            stage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
            stage.setTitle("Raktár");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
            logger.info("A bejelentkezési képernyő sikeresen betöltődött.");
        }
        catch(Exception e) {
            logger.error(e.toString());
        }
    }

    /**
     * Visszaadja a stage példányunkat.
     * @return a stage példány.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Az applikáció belépési pontja.
     * @param args parancssori argumentum.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
