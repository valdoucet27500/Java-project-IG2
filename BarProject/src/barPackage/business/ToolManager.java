package barPackage.business;

import barPackage.business.interfaces.Manager;
import barPackage.dataAccess.utils.DAO;
import barPackage.utils.CRUDItems;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Tool;
import javafx.collections.ObservableList;

import java.util.HashSet;

public class ToolManager implements Manager<Tool> {
    DAO dao;

    public ToolManager() {
        dao = DataConfiguration.getDAO(CRUDItems.TOOL);
    }

    public void add(Tool tool) throws AddErrorException {
        dao.add(tool);
    }

    public ObservableList<Tool> getAll() throws ReadErrorException {
        return dao.getAllRows();
    }

    public void delete(Tool tool) throws DeleteErrorException {
        dao.delete(tool);
    }

    public void update(Tool tool, Tool newTool) throws UpdateErrorException {
        dao.update(tool, newTool);
    }

    public HashSet<String> getColumnsNames() throws ReadErrorException {
        return dao.getColumnsNames();
    }
}