package barPackage.business;

import barPackage.dataAccess.utils.ConsumableTypeDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.ConsumableType;
import javafx.collections.ObservableList;

public class ConsumableTypeManager {
    private ConsumableTypeDataAccess consumableTypeDataAccess;

    public ConsumableTypeManager() {
        consumableTypeDataAccess = DataConfiguration.getConsumableTypeDataAccess();
    }
    public void addConsumableType(ConsumableType consumableType) throws AddErrorException {
        consumableTypeDataAccess.addConsumableType(consumableType);
    }
    public ObservableList<ConsumableType> getAllConsumableTypes() throws ReadErrorException {
        return consumableTypeDataAccess.getAllConsumableTypes();
    }
    public void deleteConsumableType(ConsumableType consumableType) throws DeleteErrorException {
        consumableTypeDataAccess.deleteConsumableType(consumableType);
    }

    public void updateConsumableType(ConsumableType consumableType, ConsumableType newConsumableType) throws UpdateErrorException {
        consumableTypeDataAccess.updateConsumableType(consumableType,newConsumableType);
    }
}
