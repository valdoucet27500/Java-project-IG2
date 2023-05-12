package barPackage.model;

import barPackage.exceptions.NumberInputValueException;

public class Ingredient {
    private static final double MIN_QUANTITY = 0;
    private static final double MAX_QUANTITY = 999999.99;
    private String consumableName;
    private String recipeName;
    private Double quantity;

    public Ingredient(String consumableName, String recipeName, Double quantity) {
        this.consumableName = consumableName;
        this.recipeName = recipeName;
        this.quantity = quantity;
    }

    public void setQuantity(Double quantity) throws NumberInputValueException {
        if (quantity < 0 || quantity > 999999.99) {
            throw new NumberInputValueException("La quantité doit être comprise entre 0 et 999999.99.", quantity, MIN_QUANTITY, MAX_QUANTITY);
        }
        this.quantity = quantity;
    }

    public String getConsumableName() {
        return consumableName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public Double getQuantity() {
        return quantity;
    }



}
