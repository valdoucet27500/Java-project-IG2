package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Unit;
import javafx.collections.ObservableList;

public interface UnitDataAccess {
    public ObservableList<Unit> getAllUnits () throws ReadErrorException;
    public void addUnit (Unit unit) throws AddErrorException;
    public void deleteUnit (Unit unit) throws DeleteErrorException;
    public void updateUnit (Unit unit, Unit newUnit) throws UpdateErrorException;
}
