package barPackage.model;

import barPackage.exceptions.NumberInputValueException;

public class Drink {
    private String drinkId;
    private String drinkType;
    private Boolean isSugarFree;
    private Boolean isSparkling;
    private Double alcoholLevel;

    public Drink(String drinkId, String drinkType, Boolean isSugarFree, Boolean isSparkling, Double alcoholLevel) throws NumberInputValueException {
        setDrinkId(drinkId);
        setDrinkType(drinkType);
        setIsSugarFree(isSugarFree);
        setIsSparkling(isSparkling);
        setAlcoholLevel(alcoholLevel);
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

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    private void setAlcoholLevel(Double alcoholLevel) throws NumberInputValueException {
        if (alcoholLevel < 0 || alcoholLevel > 100) {
            throw new NumberInputValueException("Le taux d'alcool doit être compris entre 0 et 100.", alcoholLevel, 0., 100.);
        }
        this.alcoholLevel = alcoholLevel;
    }

    public String getDrinkId() {
        return drinkId;
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
