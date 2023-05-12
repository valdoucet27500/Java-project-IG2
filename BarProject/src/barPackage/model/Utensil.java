package barPackage.model;

public class Utensil {
    private String toolName;
    private String recipeName;

    public Utensil(String toolName, String recipeName) {
        this.toolName = toolName;
        this.recipeName = recipeName;
    }

    public String getToolName() {
        return toolName;
    }

    public String getRecipeName() {
        return recipeName;
    }

}
