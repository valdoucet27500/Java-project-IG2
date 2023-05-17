package barPackage.controller.drinkType;

import barPackage.business.DrinkTypeManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.DrinkType;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.DrinkTypeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class DeleteDrinkType {
    @FXML
    private Button cancelBtn;

    @FXML
    private TableView<DrinkType> tableView;

    @FXML
    private Button deleteBtn;

    @FXML
    private AnchorPane primaryPan;
    @FXML
    private void initialize() {
        try {
            TableColumn<DrinkType,String> nameColumn = new TableColumn<>(" Nom");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableView.getColumns().add(nameColumn);
            DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
            for (DrinkType drinkType : drinkTypeManager.getAllDrinkTypes()) {
                tableView.getItems().add(drinkType);
            }
        } catch (ReadErrorException e) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onCancelBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onDeleteBtnClick() {
        try {
            if (tableView.getSelectionModel().getSelectedItem() == null) {
                DrinkTypeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez selectionner un type à supprimer").showAndWait();
            } else {
                DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
                drinkTypeManager.deleteDrinkType(tableView.getSelectionModel().getSelectedItem());
                DrinkTypeAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                tableView.getItems().clear();
                for (DrinkType consumableType : drinkTypeManager.getAllDrinkTypes()) {
                    tableView.getItems().add(consumableType);
                }
            }
        } catch (ReadErrorException | DeleteErrorException e) {
            DrinkTypeAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }
}
