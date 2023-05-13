package barPackage.controller.consumable;

import barPackage.business.ConsumableManager;
import barPackage.business.DrinkManager;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Consumable;
import barPackage.model.Drink;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class DeleteConsumable {
    @FXML
    private Button cancelBtn;
    @FXML
    private TableView<Consumable> tableView;
    @FXML
    private Button deleteBtn;
    @FXML
    private AnchorPane primaryPan;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private void initialize() {
        try {
            comboBox.getItems().addAll("Boisson", "Consommable");
            TableColumn<Consumable, String> consumableNameColumn = new TableColumn<>(" Name");
            TableColumn<Consumable, Boolean> isVeganColumn = new TableColumn<>("Vegan");
            TableColumn<Consumable, String> descriptionColumn = new TableColumn<>("Description");
            TableColumn<Consumable, String> unitIDColumn = new TableColumn<>("Unit");
            TableColumn<Consumable, Double> kcalColumn = new TableColumn<>("Kcal");
            TableColumn<Consumable, LocalDate> createdDateColumn = new TableColumn<>("Created Date");
            TableColumn<Consumable, String> consumableTypeIDColumn = new TableColumn<>("Type");
            consumableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            isVeganColumn.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            unitIDColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
            createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            consumableTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.getColumns().addAll(consumableNameColumn, isVeganColumn, unitIDColumn, kcalColumn,
                    createdDateColumn, consumableTypeIDColumn, descriptionColumn);
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumables()) {
                tableView.getItems().add(consumable);
            }
        } catch (ReadErrorException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
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
            if (tableView.getSelectionModel() == null) {
                ConsumableAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, "Veuillez selectionner un consommable à supprimer").showAndWait();
            } else {
                ConsumableManager consumableManager = new ConsumableManager();
                consumableManager.deleteConsumable(tableView.getSelectionModel().getSelectedItem());
                ConsumableAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                tableView.getItems().clear();
                for (Consumable consumable : consumableManager.getAllConsumables()) {
                    tableView.getItems().add(consumable);
                }
            }
        } catch (ReadErrorException | DeleteErrorException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.DELETE_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onComboBoxChange() {
        try {
            tableView.getItems().clear();
            ConsumableManager consumableManager = new ConsumableManager();
            DrinkManager drinkManager = new DrinkManager();
            if (comboBox.getSelectionModel().getSelectedItem().equals("Boisson")) {
                for (Drink drink : drinkManager.getAllDrinks()) {
                    tableView.getItems().add(drink);
                }
            }
            if (comboBox.getSelectionModel().getSelectedItem().equals("Consommable")) {
                for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                            tableView.getItems().add(consumable);
                }
            }
        } catch (ReadErrorException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

}
