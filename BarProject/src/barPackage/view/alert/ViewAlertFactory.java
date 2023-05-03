package barPackage.view.alert;

import javafx.scene.control.Alert;

public class ViewAlertFactory {
    public static Alert getAlert(AlertFactoryType type, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de chargement");
        alert.setContentText("Une erreur est survenue lors du chargement de la page.\n" + message);
        return alert;
    }

    public static Alert getAlert(AlertFactoryType type) {
        return getAlert(type, "");
    }
}
