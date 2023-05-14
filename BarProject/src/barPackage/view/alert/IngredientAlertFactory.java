package barPackage.view.alert;

import javafx.scene.control.Alert;

public class IngredientAlertFactory {
    public static Alert getAlert(AlertFactoryType type, String message) {
        Alert alert;
        switch (type) {
            case ADD_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout réussi");
                alert.setContentText("L'ingrédient a été ajouté avec succès a la recette.");
            }
            case ADD_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'ajout");
                alert.setContentText("Une erreur est survenue lors de l'ajout de l'ingrédient.\n" + message);
            }
            case DELETE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Suppression réussie");
                alert.setContentText("L'ingrédient a été supprimé avec succès de la recette.");
            }
            case DELETE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de suppression");
                alert.setContentText("Une erreur est survenue lors de la suppression de l'ingrédient de la recette.\n" + message);
            }
            case READ_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de lecture");
                alert.setContentText("Une erreur est survenue lors de la lecture de la base de données.\n" + message);
            }
            case UPDATE_PASS -> {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modification réussie");
                alert.setContentText("L'ingrédient a été modifié avec succès dans la recette.");
            }
            case UPDATE_FAIL -> {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de modification");
                alert.setContentText("Une erreur est survenue lors de la modification de l'ingrédient dans la recette.\n" + message);
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
