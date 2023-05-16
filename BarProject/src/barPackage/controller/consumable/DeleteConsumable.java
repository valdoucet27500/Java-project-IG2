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
            comboBox.getSelectionModel().select("Consommable");
            setTableViewConsumable();
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
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
                if (comboBox.getSelectionModel().getSelectedItem().equals("Boisson")) {
                    DrinkManager drinkManager = new DrinkManager();
                    drinkManager.deleteDrink((Drink) tableView.getSelectionModel().getSelectedItem());
                } else {
                    ConsumableManager consumableManager = new ConsumableManager();
                    consumableManager.deleteConsumable(tableView.getSelectionModel().getSelectedItem());
                };
                ConsumableAlertFactory.getAlert(AlertFactoryType.DELETE_PASS).showAndWait();
                deleteBtn.setDisable(true);
                tableView.getItems().clear();
                if (comboBox.getSelectionModel().getSelectedItem().equals("Boisson")) {
                    DrinkManager drinkManager = new DrinkManager();
                    setTableViewDrink();
                    for (Drink drink : drinkManager.getAllDrinks()) {
                        tableView.getItems().add(drink);
                    }
                } else {
                    ConsumableManager consumableManager = new ConsumableManager();
                    setTableViewConsumable();
                    for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                        tableView.getItems().add(consumable);
                    }
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
                setTableViewDrink();
                deleteBtn.setDisable(true);
                for (Drink drink : drinkManager.getAllDrinks()) {
                    tableView.getItems().add(drink);
                }
            }
            if (comboBox.getSelectionModel().getSelectedItem().equals("Consommable")) {
                setTableViewConsumable();
                deleteBtn.setDisable(true);
                for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                            tableView.getItems().add(consumable);
                }
            }
        } catch (ReadErrorException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
    public void setTableViewConsumable(){
        tableView.getColumns().clear();
        TableColumn<Consumable, String> consumableNameColumn = new TableColumn<>(" Nom");
        TableColumn<Consumable, Boolean> isVeganColumn = new TableColumn<>("Vegan");
        TableColumn<Consumable, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Consumable, String> unitIDColumn = new TableColumn<>("Unité");
        TableColumn<Consumable, Double> kcalColumn = new TableColumn<>("Kcal");
        TableColumn<Consumable, LocalDate> createdDateColumn = new TableColumn<>("Date de création");
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
    }
    public void setTableViewDrink(){
        tableView.getColumns().clear();
        TableColumn<Consumable, String> drinkNameColumn = new TableColumn<>(" Nom");
        TableColumn<Consumable, Boolean> isVeganColumn = new TableColumn<>("Vegan");
        TableColumn<Consumable, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Consumable, String> unitIDColumn = new TableColumn<>("Unité");
        TableColumn<Consumable, Double> kcalColumn = new TableColumn<>("Kcal");
        TableColumn<Consumable, LocalDate> createdDateColumn = new TableColumn<>("Date de création");
        TableColumn<Consumable, String> consumableTypeIDColumn = new TableColumn<>("Type");
        TableColumn<Consumable, String> drinkTypeIDColumn = new TableColumn<>("Type de boisson");
        TableColumn<Consumable, Double> alcoholDegreeColumn = new TableColumn<>("Degré d'alcool");
        TableColumn<Consumable, Boolean> isSparklingColumn = new TableColumn<>("Pétillant");
        TableColumn<Consumable, Boolean> isSugarFreeColumn = new TableColumn<>("Sans sucre");
        drinkNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        isVeganColumn.setCellValueFactory(new PropertyValueFactory<>("isVegan"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        unitIDColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        kcalColumn.setCellValueFactory(new PropertyValueFactory<>("kcal"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        consumableTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        drinkTypeIDColumn.setCellValueFactory(new PropertyValueFactory<>("drinkType"));
        alcoholDegreeColumn.setCellValueFactory(new PropertyValueFactory<>("alcoholLevel"));
        isSparklingColumn.setCellValueFactory(new PropertyValueFactory<>("isSparkling"));
        isSugarFreeColumn.setCellValueFactory(new PropertyValueFactory<>("isSugarFree"));
        tableView.getColumns().addAll(drinkNameColumn, isVeganColumn, unitIDColumn, kcalColumn,
                createdDateColumn, consumableTypeIDColumn, descriptionColumn, drinkTypeIDColumn, alcoholDegreeColumn,
                isSparklingColumn, isSugarFreeColumn);
    }
    @FXML
    public void onTableViewSelect(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            deleteBtn.setDisable(false);
        }
    }
}
