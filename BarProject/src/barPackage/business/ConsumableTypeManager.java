package barPackage.business;

import barPackage.business.interfaces.Manager;
import barPackage.dataAccess.utils.DAO;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.ConsumableType;
import javafx.collections.ObservableList;

import java.util.HashSet;

public class ConsumableTypeManager implements Manager<ConsumableType> {
    private DAO dao;

    public ConsumableTypeManager() {
        dao = DataConfiguration.getConsumableTypeDataAccess();
    }
    public void add(ConsumableType consumableType) throws AddErrorException {
        dao.add(consumableType);
    }
    public ObservableList<ConsumableType> getAll() throws ReadErrorException {
        return dao.getAllRows();
    }
    public void delete(ConsumableType consumableType) throws DeleteErrorException {
        dao.delete(consumableType);
    }

    public void update(ConsumableType consumableType, ConsumableType newConsumableType) throws UpdateErrorException {
        dao.update(consumableType,newConsumableType);
    }

    public HashSet<String> getColumnsNames() throws ReadErrorException {
        return dao.getColumnsNames();
    }
}
