package barPackage.dataAccess;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.ConsumableType;
import javafx.collections.ObservableList;

public interface ConsumableTypeDataAccess {
    public ObservableList<ConsumableType> getAllConsumableTypes () throws ReadErrorException;
    public void addConsumableType (ConsumableType consumableType) throws AddErrorException;
    public void deleteConsumableType (ConsumableType consumableType) throws DeleteErrorException;
    public void updateConsumableType (ConsumableType consumableType, ConsumableType newConsumableType) throws UpdateErrorException;
}
