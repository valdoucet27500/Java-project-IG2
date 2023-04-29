package barPackage.business;

import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.dataAccess.utils.UnitDataAccess;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Unit;
import javafx.collections.ObservableList;

public class UnitManager {
    private UnitDataAccess unitDataAccess;

    public UnitManager() {
        unitDataAccess = DataConfiguration.getUnitDataAccess();
    }
    public void addUnit(Unit unit) throws AddErrorException {
        unitDataAccess.addUnit(unit);
    }
    public ObservableList<Unit> getAllUnits() throws ReadErrorException {
        return unitDataAccess.getAllUnits();
    }
    public void deleteUnit(Unit unit) throws DeleteErrorException {
        unitDataAccess.deleteUnit(unit);
    }

    public void updateUnit(Unit unit, Unit newUnit) throws UpdateErrorException {
        unitDataAccess.updateUnit(unit,newUnit);
    }
}
