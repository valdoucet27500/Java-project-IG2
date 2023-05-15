package barPackage.model;

public class MissingIngredient {
    private String name;
    private double quantity;
    private double requiredQuantity;

    public MissingIngredient(String name, double quantity, double requiredQuantity) {
        this.name = name;
        this.quantity = quantity;
        this.requiredQuantity = requiredQuantity;
    }
    public String getName() {
        return name;
    }
    public double getQuantity() {
        return quantity;
    }
    public double getRequiredQuantity() {
        return requiredQuantity;
    }
}
