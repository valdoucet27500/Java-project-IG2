package barPackage.controller;

import barPackage.business.ManagerFactory;
import barPackage.utils.CRUDItems;
import barPackage.utils.ManagementActions;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ManagementController {
    @FXML
    private ComboBox<String> actionComboBox;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> elementComboBox;

    @FXML
    private AnchorPane formPan;

    @FXML
    private TableColumn<Object, Boolean> selectionColumn;

    @FXML
    private TableView<Object> tableView;

    @FXML
    private Button validationBtn;

    @FXML
    private Button refreshBtn;

    @FXML
    public void initialize() {
        for (ManagementActions action : ManagementActions.values()) {
            actionComboBox.getItems().add(action.getName());
        }
        actionComboBox.setValue(ManagementActions.VIEW.getName());
        for (CRUDItems item : CRUDItems.values()) {
            elementComboBox.getItems().add(item.getName());
        }
    }

    public void setItemSelection(CRUDItems item) {
        actionComboBox.setValue(item.getName());
    }

    public void validationBtnClick() {
        if (!actionComboBox.getValue().equals("")) {
            try {
                ManagementActions action = ManagementActions.getConstant(actionComboBox.getValue());
                CRUDItems item = CRUDItems.getConstant(elementComboBox.getValue());
                switch (action) {
                    case VIEW:
//                        for (String s : ManagerFactory.getManager(item).getColumnsNames()) {
//                            System.out.println(s);
//                        }
                        break;
                    case CREATE:
                        System.out.println("CREATE");
                        break;
                    case DELETE:
                        System.out.println("DELETE");
                        break;
                    case UPDATE:
                        System.out.println("UPDATE");
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
