package barPackage.model;

import barPackage.exceptions.StringInputSizeException;
import javafx.beans.property.SimpleStringProperty;

public class DrinkType {
    private final static int MAX_SIZE = 32;
    private SimpleStringProperty name;

    public DrinkType(String name) throws StringInputSizeException {
        setName(name);
    }
    public void setName (String name) throws StringInputSizeException {
        if (name.length() > MAX_SIZE) {
            throw new StringInputSizeException("La taille du nom du type de boisson est trop grande.", name.length(), MAX_SIZE);
        } else if (name.length() == 0) {
            throw new StringInputSizeException("Le nom du type de boisson ne peut pas être vide.", 0, MAX_SIZE);
        }
        this.name = new SimpleStringProperty(name);
    }
    public String getName() {
        return name.get();
    }
}
