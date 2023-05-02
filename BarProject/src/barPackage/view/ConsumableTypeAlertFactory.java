package barPackage.view;

import javafx.scene.control.Alert;

public class ConsumableTypeAlertFactory {
    public static Alert getAlert(AlertFactoryType type) {
        Alert alert;
        switch (type) {
            case ADD_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout réussi");
                alert.setContentText("Le type de consommable a été ajouté avec succès.");
            }
            case ADD_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'ajout");
                alert.setContentText("Une erreur est survenue lors de l'ajout du type de consommable");
            }
            case DELETE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression réussie");
                alert.setContentText("Le type de consommable a été supprimé avec succès.");
            }
            case DELETE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de suppression");
                alert.setContentText("Une erreur est survenue lors de la suppression du type de consommable");
            }
            case READ_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de lecture");
                alert.setContentText("Une erreur est survenue lors de la lecture de la base de données");
            }
            case UPDATE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification réussie");
                alert.setContentText("Le type de consommable a été modifié avec succès.");
            }
            case UPDATE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setContentText("Une erreur est survenue lors de la modification du type de consommable");
            }
            default -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Une erreur est survenue");
            }
        }
        return alert;
    }
}
