package barPackage.model;

import barPackage.exceptions.StringInputSizeException;

public class DrinkType {
    private final static int MAX_NAME_SIZE = 32;
    private String name;

    public DrinkType(String name) throws StringInputSizeException {
        setName(name);
    }
    public void setName (String name) throws StringInputSizeException {
        if (name.length() > MAX_NAME_SIZE) {
            throw new StringInputSizeException("La taille du nom du type de boisson est trop grande.", name.length(), MAX_NAME_SIZE);
        } else if (name.length() == 0) {
            throw new StringInputSizeException("Le nom du type de boisson ne peut pas être vide.", 0, MAX_NAME_SIZE);
        }
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
