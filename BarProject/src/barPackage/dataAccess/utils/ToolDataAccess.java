package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Tool;
import javafx.collections.ObservableList;


public interface ToolDataAccess {


    public ObservableList<Tool> getAllTools () throws ReadErrorException;
    public void addTool (Tool tool) throws AddErrorException;
    public void deleteTool(Tool tool) throws DeleteErrorException;
    void updateTool(Tool tool, Tool newTool) throws UpdateErrorException;
}
