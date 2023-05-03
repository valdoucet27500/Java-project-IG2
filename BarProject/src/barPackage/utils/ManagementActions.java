package barPackage.utils;

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

    public static ManagementActions getConstant(String name) {
        for (ManagementActions constant : ManagementActions.values()) {
            if (constant.getName().equals(name)) {
                return constant;
            }
        }
        return null;
    }

}
