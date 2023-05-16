package barPackage.controller.content;

import barPackage.business.ContentManager;
import barPackage.model.Outdate;
import barPackage.view.alert.AlertFactoryType;
import barPackage.view.alert.ViewAlertFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

public class OutdateContent {
    @FXML
    private Button backBtn;

    @FXML
    private DatePicker outdatePicker;

    @FXML
    private AnchorPane primaryPan;

    @FXML
    private TableView<Outdate> tableView;
    public void initialize() {
        outdatePicker.setValue(LocalDate.now());
        TableColumn<Outdate, String> consumableNameColumn = new TableColumn<>("Nom");
        TableColumn<Outdate, String> ExpirationDateColumn = new TableColumn<>("Date d'expiration");
        TableColumn<Outdate,String> consumableTypeNameColumn = new TableColumn<>("Type");
        consumableNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ExpirationDateColumn.setCellValueFactory(new PropertyValueFactory<>("outdate"));
        consumableTypeNameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableView.getColumns().addAll(consumableNameColumn, ExpirationDateColumn, consumableTypeNameColumn);
        try {
            ContentManager contentManager = new ContentManager();
            tableView.getItems().addAll(contentManager.getAllOutdatedContent(outdatePicker.getValue()));
        } catch (Exception e) {
            ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onBackBtnClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../view/MainView.fxml")));
            Parent root = fxmlLoader.load();
            primaryPan.getScene().setRoot(root);
        } catch (IOException e) {
            ViewAlertFactory.getAlert(AlertFactoryType.PAGE_LOAD_FAIL, e.getMessage()).showAndWait();
        }
    }
    @FXML
    public void onOutdatePickerChange() {
        tableView.getItems().clear();
        try {
            ContentManager contentManager = new ContentManager();
            tableView.getItems().addAll(contentManager.getAllOutdatedContent(outdatePicker.getValue()));
        } catch (Exception e) {
            ViewAlertFactory.getAlert(AlertFactoryType.READ_FAIL, e.getMessage()).showAndWait();
        }
    }
}
