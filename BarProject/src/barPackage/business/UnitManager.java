package barPackage.business;

import barPackage.business.interfaces.Manager;
import barPackage.dataAccess.utils.DAO;
import barPackage.utils.CRUDItems;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Unit;
import javafx.collections.ObservableList;

import java.util.HashSet;

public class UnitManager implements Manager<Unit> {
    private DAO dao;

    public UnitManager() {
        dao = DataConfiguration.getDAO(CRUDItems.UNIT);
    }
    public void add(Unit unit) throws AddErrorException {
        dao.add(unit);
    }
    public ObservableList<Unit> getAll() throws ReadErrorException {
        return dao.getAllRows();
    }
    public void delete(Unit unit) throws DeleteErrorException {
        dao.delete(unit);
    }

    public void update(Unit unit, Unit newUnit) throws UpdateErrorException {
        dao.update(unit,newUnit);
    }

    public HashSet<String> getColumnsNames() throws ReadErrorException {
        return dao.getColumnsNames();
    }
}
