package barPackage.business;

import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.dataAccess.utils.ToolDataAccess;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Tool;
import javafx.collections.ObservableList;

public class ToolManager {
    ToolDataAccess toolDataAccess;

    public ToolManager() {
        toolDataAccess = DataConfiguration.getToolDataAccess();
    }

    public void addTool(Tool tool) throws AddErrorException {
        toolDataAccess.addTool(tool);
    }

    public ObservableList<Tool> getAllTools() throws ReadErrorException {
        return toolDataAccess.getAllTools();
    }

    public void deleteTool(Tool tool) throws DeleteErrorException {
        toolDataAccess.deleteTool(tool);
    }

    public void updateTool(Tool tool, Tool newTool) throws UpdateErrorException {
        toolDataAccess.updateTool(tool, newTool);
    }
}