package barPackage.model;

import barPackage.exceptions.NumberInputValueException;
import barPackage.exceptions.StringInputSizeException;

public class Drink extends Consumable {
    private String drinkType;
    private Boolean isSugarFree;
    private Boolean isSparkling;
    private Double alcoholLevel;

    public Drink(String name, Boolean isVegan, String description, String unit, Double kcal, String consumableType, String drinkType, Double alcoholLevel, Boolean isSparkling, Boolean isSugarFree) throws NumberInputValueException, StringInputSizeException {
        super(name, isVegan, description, unit, kcal, consumableType);
        this.setDrinkType(drinkType);
        this.setAlcoholLevel(alcoholLevel);
        this.setIsSparkling(isSparkling);
        this.setIsSugarFree(isSugarFree);
    }

    private void setIsSugarFree(Boolean isSugarFree) {
        this.isSugarFree = isSugarFree;
    }

    private void setIsSparkling(Boolean isSparkling) {
        this.isSparkling = isSparkling;
    }

    private void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    private void setAlcoholLevel(Double alcoholLevel) throws NumberInputValueException {
        if (alcoholLevel < 0 || alcoholLevel > 100) {
            throw new NumberInputValueException("Le taux d'alcool doit être compris entre 0 et 100.", alcoholLevel, 0., 100.);
        }
        this.alcoholLevel = alcoholLevel;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public Boolean getIsSugarFree() {
        return isSugarFree;
    }

    public Boolean getIsSparkling() {
        return isSparkling;
    }

    public Double getAlcoholLevel() {
        return alcoholLevel;
    }
}
