package barPackage.controller.content;

import barPackage.business.ContentManager;
import barPackage.model.Content;
import barPackage.view.alert.AlertFactoryType;
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

public class ViewContent {
    @FXML
    private AnchorPane primaryPan;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Content> tableViewConsumable;
    @FXML
    private void initialize() {
            TableColumn<Content, String> contentIdColumn = new TableColumn<>("Id");
            TableColumn<Content, String> consumableNameColumn = new TableColumn<>("Nom");
            TableColumn<Content, String> quantityColumn = new TableColumn<>("Quantité");
            TableColumn<Content, String> unitColumn = new TableColumn<>("Unité");
            TableColumn<Content, String> expirationDateColumn = new TableColumn<>("Expiration");
            contentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            consumableNameColumn.setCellValueFactory(new PropertyValueFactory<>("consumableName"));
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
            expirationDateColumn.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
            tableViewConsumable.getColumns().addAll(contentIdColumn, consumableNameColumn, quantityColumn, unitColumn, expirationDateColumn);
            try {
                ContentManager contentManager = new ContentManager();
                for (Content content : contentManager.getAllContentAvailables()) {
                    tableViewConsumable.getItems().add(content);
                }
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
}
