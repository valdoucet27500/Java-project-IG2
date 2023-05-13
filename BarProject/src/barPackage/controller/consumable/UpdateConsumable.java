package barPackage.controller.consumable;

import barPackage.business.*;
import barPackage.exceptions.NumberInputValueException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.StringInputSizeException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.*;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ConsumableAlertFactory;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class UpdateConsumable {
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
    private AnchorPane primaryPan;

    @FXML
    private CheckBox sparklingCheck;

    @FXML
    private Text sparklingText;

    @FXML
    private CheckBox sugarFreeCheck;

    @FXML
    private Text sugarFreeText;

    @FXML
    private TableView<Consumable> tableView;

    @FXML
    private ComboBox<String> unitComboBox;

    @FXML
    private Button updateBtn;

    @FXML
    private CheckBox veganCheck;

    @FXML
    private ComboBox<String> selectCombobox;
    private Boolean isDrink = false;
    @FXML
    private void initialize() {
        try {
            selectCombobox.getItems().addAll("Boisson", "Consommable");
            selectCombobox.getSelectionModel().select("Consommable");
            setTableViewConsumable();
            ConsumableManager consumableManager = new ConsumableManager();
            for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                tableView.getItems().add(consumable);
            }
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
    public void onTableViewSelect(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            updateBtn.setVisible(true);
            Consumable consumable = tableView.getSelectionModel().getSelectedItem();
            nameText.setText(consumable.getName());
            veganCheck.setSelected(consumable.getIsVegan());
            descriptionText.setText(consumable.getDescription());
            unitComboBox.getSelectionModel().select(consumable.getUnit());
            kcalText.setText(consumable.getKcal().toString());
            consumableTypeCombobox.getSelectionModel().select(consumable.getType());
            if(isDrink){
                Drink drink = (Drink) tableView.getSelectionModel().getSelectedItem();
                drinkTypeCombobox.getSelectionModel().select(drink.getDrinkType());
                alcoholDegreeTextField.setText(drink.getAlcoholLevel().toString());
                sparklingCheck.setSelected(drink.getIsSparkling());
                sugarFreeCheck.setSelected(drink.getIsSugarFree());
            }
        }
    }
    @FXML
    public void onUpdateBtnClick() {
        try {
            if (tableView.getSelectionModel() == null) {
                ConsumableAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, "Veuillez selectionner un consommable à modifier").showAndWait();
            } else {
                ConsumableManager consumableManager = new ConsumableManager();
                String name = nameText.getText();
                Boolean isVegan = veganCheck.isSelected();
                String description = null;
                if (descriptionText.getText() != null){
                    description = descriptionText.getText().equals("") ? null : descriptionText.getText();
                }
                String unit = unitComboBox.getSelectionModel().getSelectedItem();
                Double kcal = kcalText.getText().equals("") ? null : Double.parseDouble(kcalText.getText());
                String consumableType = consumableTypeCombobox.getSelectionModel().getSelectedItem();
                // Drink
                String drinkType = drinkTypeCombobox.getSelectionModel().getSelectedItem();
                Double alcoholDegree = alcoholDegreeTextField.getText().equals("") ? null : Double.parseDouble(alcoholDegreeTextField.getText());
                Boolean isSparkling = sparklingCheck.isSelected();
                Boolean isSugarFree = sugarFreeCheck.isSelected();
                if (isDrink) {
                    Drink drink = new Drink(name, isVegan, description, unit, kcal, consumableType, drinkType, alcoholDegree, isSparkling, isSugarFree);
                    DrinkManager drinkManager = new DrinkManager();
                    drinkManager.updateDrink((Drink) tableView.getSelectionModel().getSelectedItem(), drink);
                } else {
                    consumableManager.updateConsumable(tableView.getSelectionModel().getSelectedItem(), new Consumable(name,
                            isVegan, description, unit, kcal, consumableType));
                }
                ConsumableAlertFactory.getAlert(AlertFactoryType.UPDATE_PASS).showAndWait();
                tableViewClear();
                if (isDrink) {
                    DrinkManager drinkManager = new DrinkManager();
                    for (Drink drink : drinkManager.getAllDrinks()) {
                        tableView.getItems().add(drink);
                    }
                } else {
                    for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                        tableView.getItems().add(consumable);
                    }
                }
            }
        } catch ( ReadErrorException | StringInputSizeException | UpdateErrorException | NumberInputValueException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.UPDATE_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onComboBoxChange() {
        try {
            tableView.getItems().clear();
            ConsumableManager consumableManager = new ConsumableManager();
            DrinkManager drinkManager = new DrinkManager();
            if (selectCombobox.getSelectionModel().getSelectedItem().equals("Boisson")) {
                isDrink = true;
                drinkCheck.setSelected(true);
                onDrinkCheck();
                setTableViewDrink();
                for (Drink drink : drinkManager.getAllDrinks()) {
                    tableView.getItems().add(drink);
                }
            }
            if (selectCombobox.getSelectionModel().getSelectedItem().equals("Consommable")) {
                isDrink = false;
                drinkCheck.setSelected(false);
                onDrinkCheck();
                setTableViewConsumable();
                for (Consumable consumable : consumableManager.getAllConsumableNoDrinks()) {
                    tableView.getItems().add(consumable);
                }
            }
        } catch (ReadErrorException e) {
            ConsumableAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
    public void tableViewClear(){
        tableView.getItems().clear();
        updateBtn.setVisible(false);
        nameText.setText("");
        veganCheck.setSelected(false);
        descriptionText.setText("");
        unitComboBox.getSelectionModel().clearSelection();
        kcalText.setText("");
        consumableTypeCombobox.getSelectionModel().clearSelection();
        drinkTypeCombobox.getSelectionModel().clearSelection();
        alcoholDegreeTextField.setText("");
        sparklingCheck.setSelected(false);
        sugarFreeCheck.setSelected(false);
    }
    public void setTableViewConsumable(){
        tableView.getColumns().clear();
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
    }
    public void setTableViewDrink(){
        tableView.getColumns().clear();
        TableColumn<Consumable, String> drinkNameColumn = new TableColumn<>(" Name");
        TableColumn<Consumable, Boolean> isVeganColumn = new TableColumn<>("Vegan");
        TableColumn<Consumable, String> descriptionColumn = new TableColumn<>("Description");
        TableColumn<Consumable, String> unitIDColumn = new TableColumn<>("Unit");
        TableColumn<Consumable, Double> kcalColumn = new TableColumn<>("Kcal");
        TableColumn<Consumable, LocalDate> createdDateColumn = new TableColumn<>("Created Date");
        TableColumn<Consumable, String> consumableTypeIDColumn = new TableColumn<>("Type");
        TableColumn<Consumable, String> drinkTypeIDColumn = new TableColumn<>("Drink Type");
        TableColumn<Consumable, Double> alcoholDegreeColumn = new TableColumn<>("Alcohol Degree");
        TableColumn<Consumable, Boolean> isSparklingColumn = new TableColumn<>("Sparkling");
        TableColumn<Consumable, Boolean> isSugarFreeColumn = new TableColumn<>("Sugar Free");
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
}
