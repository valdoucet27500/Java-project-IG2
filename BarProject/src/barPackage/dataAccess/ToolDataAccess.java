package barPackage.dataAccess;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.model.Tool;
import javafx.collections.ObservableList;


public interface ToolDataAccess {


    public ObservableList<Tool> getAllTools () throws ReadErrorException;
    public void addTool (Tool tool) throws AddErrorException;
    public void deleteTool(Tool tool) throws DeleteErrorException;

}