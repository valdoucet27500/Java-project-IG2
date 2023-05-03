package barPackage.controller.utils;

public enum ManagementActions {
    VIEW("Consultation"),
    CREATE("Création"),
    DELETE("Suppression"),
    UPDATE("Modification");

    private final String name;

    ManagementActions(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
