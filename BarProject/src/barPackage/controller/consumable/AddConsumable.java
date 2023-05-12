package barPackage.controller.consumable;

import barPackage.business.*;
import barPackage.exceptions.*;
import barPackage.model.*;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableAlertFactory;
import barPackage.view.alert.ConsumableTypeAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class AddConsumable {

    @FXML
    private Text alcoholDegreeText;

    @FXML
    private TextField alcoholDegreeTextField;

    @FXML
    private ComboBox<String> consumableTypeCombobox;

    @FXML
    private TextArea descriptionText;

    @FXML
    private CheckBox drinkCheck;

    @FXML
    private ComboBox<String> drinkTypeCombobox;

    @FXML
    private Text drinkTypeText;

    @FXML
    private TextField kcalText;

    @FXML
    private TextField nameText;

    @FXML
    private CheckBox sparklingCheck;

    @FXML
    private Text sparklingText;

    @FXML
    private CheckBox sugarFreeCheck;

    @FXML
    private Text sugarFreeText;

    @FXML
    private ComboBox<String> unitComboBox;

    @FXML
    private CheckBox veganCheck;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private void initialize() {
        try {
            ConsumableTypeManager consumableTypeManager = new ConsumableTypeManager();
            for (ConsumableType consumableType : consumableTypeManager.getAllConsumableTypes()) {
                consumableTypeCombobox.getItems().add(consumableType.getName());
            }
            DrinkTypeManager drinkTypeManager = new DrinkTypeManager();
            for (DrinkType drinkType : drinkTypeManager.getAllDrinkTypes()) {
                drinkTypeCombobox.getItems().add(drinkType.getName());
            }
            UnitManager unitManager = new UnitManager();
            for (Unit unit : unitManager.getAllUnits()) {
                unitComboBox.getItems().add(unit.getName());
            }
        } catch (ReadErrorException e) {
            ConsumableTypeAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void onDrinkCheck() {
        boolean check = drinkCheck.isSelected();
        drinkTypeCombobox.setVisible(check);
        drinkTypeText.setVisible(check);
        alcoholDegreeText.setVisible(check);
        alcoholDegreeTextField.setVisible(check);
        sparklingCheck.setVisible(check);
        sparklingText.setVisible(check);
        sugarFreeCheck.setVisible(check);
        sugarFreeText.setVisible(check);
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
    public void onAddBtnClick() throws DeleteErrorException {
        // Consumable
        String name = nameText.getText();
        Boolean isVegan = veganCheck.isSelected();
        String description = descriptionText.getText().equals("") ? null : descriptionText.getText();
        String unit = unitComboBox.getSelectionModel().getSelectedItem();
        Double kcal = kcalText.getText().equals("") ? null : Double.parseDouble(kcalText.getText());
        String consumableType = consumableTypeCombobox.getSelectionModel().getSelectedItem();
        // Drink
        String drinkType = drinkTypeCombobox.getSelectionModel().getSelectedItem();
        Double alcoholDegree = alcoholDegreeTextField.getText().equals("") ? null : Double.parseDouble(alcoholDegreeTextField.getText());
        Boolean isSparkling = sparklingCheck.isSelected();
        Boolean isSugarFree = sugarFreeCheck.isSelected();
        try {
            if (drinkCheck.isSelected()) {
                Drink drink = new Drink(name, isVegan, description, unit, kcal, consumableType, drinkType, alcoholDegree, isSparkling, isSugarFree);
                DrinkManager drinkManager = new DrinkManager();
                drinkManager.addDrink(drink);
            } else {
                Consumable consumable = new Consumable(name, isVegan, description, unit, kcal, consumableType);
                ConsumableManager consumableManager = new ConsumableManager();
                consumableManager.addConsumable(consumable);
            }
            ConsumableAlertFactory.getAlert(AlertFactoryType.ADD_PASS, name).showAndWait();
        } catch (AddErrorException | StringInputSizeException | NumberInputValueException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.ADD_FAIL, e.getMessage()).showAndWait();
        }
    }
}
