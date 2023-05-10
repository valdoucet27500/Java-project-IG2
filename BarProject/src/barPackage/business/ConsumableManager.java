package barPackage.business;

import barPackage.dataAccess.utils.ConsumableDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.model.Consumable;

public class ConsumableManager {
    private ConsumableDataAccess consumableDataAccess;
    public ConsumableManager() {
        consumableDataAccess = DataConfiguration.getConsumableDataAccess();
    }

    public void addConsumable(Consumable consumable) throws AddErrorException {
        consumableDataAccess.addConsumable(consumable);
    }
}
