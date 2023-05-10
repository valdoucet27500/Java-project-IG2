package barPackage.dataAccess.utils;

import barPackage.dataAccess.db.*;

public class DataConfiguration {
    private final static String DATA_PERSISTENCE = "DB"; // "DB" or "XML"

    public static ToolDataAccess getToolDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new ToolDBAccess();
        } else {
            return null;
        }
    }

    public static UnitDataAccess getUnitDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new UnitDBAccess();
        } else {
            return null;
        }
    }

    public static DrinkTypeDataAccess getDrinkTypeDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new DrinkTypeDBAccess();
        } else {
            return null;
        }
    }

    public static ConsumableTypeDataAccess getConsumableTypeDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new ConsumableTypeDBAccess();
        } else {
            return null;
        }
    }

    public static ConsumableDataAccess getConsumableDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new ConsumableDBAccess();
        } else {
            return null;
        }
    }

    public static DrinkDataAccess getDrinkDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new DrinkDBAccess();
        } else {
            return null;
        }
    }
}
