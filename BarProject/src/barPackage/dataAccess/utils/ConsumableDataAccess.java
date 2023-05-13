package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.Consumable;
import javafx.collections.ObservableList;

public interface ConsumableDataAccess {
    public ObservableList<Consumable> getAllConsumables () throws ReadErrorException;
    public void addConsumable (Consumable consumable) throws AddErrorException;
    public void deleteConsumable (Consumable consumable) throws DeleteErrorException;
    public void updateConsumable (Consumable consumable, Consumable newConsumable) throws UpdateErrorException;
    ObservableList<Consumable> getAllConsumableNoDrinks() throws ReadErrorException;
    Consumable getConsumableByName(String name) throws ReadErrorException;
}
