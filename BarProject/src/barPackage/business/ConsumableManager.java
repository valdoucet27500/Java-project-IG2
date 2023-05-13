package barPackage.business;

import barPackage.dataAccess.utils.ConsumableDataAccess;
import barPackage.dataAccess.utils.DataConfiguration;
import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Consumable;
import javafx.collections.ObservableList;

public class ConsumableManager {
    private ConsumableDataAccess consumableDataAccess;
    public ConsumableManager() {
        consumableDataAccess = DataConfiguration.getConsumableDataAccess();
    }

    public void addConsumable(Consumable consumable) throws AddErrorException {
        consumableDataAccess.addConsumable(consumable);
    }

    public void deleteConsumable(Consumable consumable) throws DeleteErrorException {
        consumableDataAccess.deleteConsumable(consumable);
    }

    public ObservableList<Consumable> getAllConsumables() throws ReadErrorException {
        return consumableDataAccess.getAllConsumables();
    }

    public void updateConsumable(Consumable consumable, Consumable newConsumable) throws UpdateErrorException {
        consumableDataAccess.updateConsumable(consumable, newConsumable);
    }

    public ObservableList<Consumable> getAllConsumableNoDrinks() throws ReadErrorException {
        return consumableDataAccess.getAllConsumableNoDrinks();
    }

    public Consumable getConsumableByName(String name) throws ReadErrorException {
        return consumableDataAccess.getConsumableByName(name);
    }
}
