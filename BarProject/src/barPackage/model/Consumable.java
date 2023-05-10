package barPackage.model;

import barPackage.exceptions.StringInputSizeException;

public class Consumable {
    private final static int MAX_NAME_SIZE = 32;
    private final static int MAX_DESCRIPTION_SIZE = 1024;
    private String name;
    private Boolean isVegan;
    private String description;
    private String unit;
    private Double kcal;
    private String type;

    public Consumable(String name, Boolean isVegan, String description, String unit, Double kcal, String type) throws StringInputSizeException {
        setName(name);
        setIsVegan(isVegan);
        setDescription(description);
        setUnit(unit);
        setKcal(kcal);
        setType(type);
    }

    public void setName (String name) throws StringInputSizeException {
        if (name.length() > MAX_NAME_SIZE) {
            throw new StringInputSizeException("La taille du nom du consommable est trop grande.", name.length(), MAX_NAME_SIZE);
        } else if (name.length() == 0) {
            throw new StringInputSizeException("Le nom du consommable ne peut pas être vide.", 0, MAX_NAME_SIZE);
        }
        this.name = name;
    }

    public void setIsVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public void setDescription(String description) throws StringInputSizeException {
        if (description != null && description.length() > MAX_DESCRIPTION_SIZE) {
            throw new StringInputSizeException("La taille de la description du consommable est trop grande.", description.length(), MAX_DESCRIPTION_SIZE);
        }
        this.description = description;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsVegan() {
        return isVegan;
    }

    public String getDescription() {
        return description;
    }

    public String getUnit() {
        return unit;
    }

    public Double getKcal() {
        return kcal;
    }

    public String getType() {
        return type;
    }
}
