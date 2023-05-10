package barPackage.view.alert;

import javafx.scene.control.Alert;

public class ConsumableAlertFactory extends ViewAlertFactory {
    public static Alert getAlert(AlertFactoryType type, String message) {
        Alert alert;
        switch (type) {
            case ADD_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout réussi");
                alert.setContentText("Le consommable a été ajouté avec succès.");
            }
            case ADD_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'ajout");
                alert.setContentText("Une erreur est survenue lors de l'ajout du consommable.\n" + message);
            }
            case DELETE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression réussie");
                alert.setContentText("Le consommable a été supprimé avec succès.");
            }
            case DELETE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de suppression");
                alert.setContentText("Une erreur est survenue lors de la suppression du consommable.\n" + message);
            }
            case READ_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de lecture");
                alert.setContentText("Une erreur est survenue lors de la lecture de la base de données.\n" + message);
            }
            case UPDATE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification réussie");
                alert.setContentText("Le consommable a été modifié avec succès.");
            }
            case UPDATE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setContentText("Une erreur est survenue lors de la modification du consommable.\n" + message);
            }
            default -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Une erreur est survenue.\n" + message);
            }
        }
        return alert;
    }

    public static Alert getAlert(AlertFactoryType type) {
        return getAlert(type, "");
    }
}
