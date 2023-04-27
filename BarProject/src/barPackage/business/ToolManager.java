package barPackage.business;

import barPackage.dataAccess.ToolDBAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.interfaces.ToolDataAccess;
import barPackage.model.Tool;
import javafx.collections.ObservableList;

public class ToolManager implements ToolDataAccess {
    ToolDataAccess toolDataAccess;

    public ToolManager() {
        toolDataAccess = new ToolDBAccess();
    }

    @Override
    public void addTool(Tool tool) throws AddErrorException {
        toolDataAccess.addTool(tool);
    }

    @Override
    public ObservableList<Tool> getAllTools() throws ReadErrorException {
        return toolDataAccess.getAllTools();
    }

    public void deleteTool(Tool tool) throws DeleteErrorException {
        toolDataAccess.deleteTool(tool);
    }
}
