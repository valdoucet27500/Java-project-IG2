package barPackage.utils;

public enum CRUDItems {
    TOOL("Matériel"),
    UNIT("Unité"),
    DRINK_TYPE("Type de boisson"),
    CONSUMABLE_TYPE("Type de consommable"),
    CONSUMABLE("Consommable"),
    RECIPE("Recette");

    private final String name;

    CRUDItems(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static CRUDItems getConstant(String name) {
        for (CRUDItems constant : CRUDItems.values()) {
            if (constant.getName().equals(name)) {
                return constant;
            }
        }
        return null;
    }

}
