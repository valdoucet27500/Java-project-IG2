package barPackage.view.alert;

import javafx.scene.control.Alert;

public class ToolAlertFactory {
    public static Alert getAlert(AlertFactoryType type) {
        Alert alert;
        switch (type) {
            case ADD_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout réussi");
                alert.setContentText("L'outils a été ajouté avec succès.");
            }
            case ADD_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'ajout");
                alert.setContentText("Une erreur est survenue lors de l'ajout de l'outil");
            }
            case DELETE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression réussie");
                alert.setContentText("L'outil a été supprimé avec succès.");
            }
            case DELETE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de suppression");
                alert.setContentText("Une erreur est survenue lors de la suppression de l'outil");
            }
            case READ_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de lecture");
                alert.setContentText("Une erreur est survenue lors de la lecture de la base de données");
            }
            case UPDATE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification réussie");
                alert.setContentText("L'outil a été modifié avec succès.");
            }
            case UPDATE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setContentText("Une erreur est survenue lors de la modification de l'outil");
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
