package barPackage.model;

import barPackage.exceptions.StringInputSizeException;
import javafx.beans.property.SimpleStringProperty;

public class Tool {
    private final static int MAX_SIZE = 32;

    private String name;

    public Tool(String name) throws StringInputSizeException {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName (String name) throws StringInputSizeException {
        if (name.length() > MAX_SIZE) {
            throw new StringInputSizeException("La taille du nom de l'outil est trop grande.", name.length(), MAX_SIZE);
        } else if (name.length() == 0) {
            throw new StringInputSizeException("Le nom de l'outil ne peut pas être vide.", 0, MAX_SIZE);
        }
        this.name = name;
    }
}
