package barPackage.dataAccess.utils;

import barPackage.dataAccess.db.ToolDBAccess;
import barPackage.dataAccess.db.UnitDBAccess;

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

    public static ConsumableTypeDataAccess getConsumableTypeDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new barPackage.dataAccess.db.ConsumableTypeDBAccess();
        } else {
            return null;
        }
    }
}
