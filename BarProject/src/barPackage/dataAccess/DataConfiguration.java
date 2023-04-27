package barPackage.dataAccess;

import barPackage.dataAccess.db.ToolDBAccess;

public class DataConfiguration {
    private final static String DATA_PERSISTENCE = "DB"; // "DB" or "XML"

    public static ToolDataAccess getToolDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new ToolDBAccess();
        } else {
            return null;
        }
    }
}
