package barPackage.dataAccess.utils;

import barPackage.exceptions.AddErrorException;
import barPackage.exceptions.DeleteErrorException;
import barPackage.exceptions.ReadErrorException;
import barPackage.exceptions.UpdateErrorException;
import barPackage.model.DrinkType;
import javafx.collections.ObservableList;

public interface DrinkTypeDataAccess {
    public ObservableList<DrinkType> getAllDrinkTypes () throws ReadErrorException;
    public void addDrinkType (DrinkType drinkType) throws AddErrorException;
    public void deleteDrinkType (DrinkType drinkType) throws DeleteErrorException;
    public void updateDrinkType (DrinkType drinkType, DrinkType newDrinkType) throws UpdateErrorException;
}
