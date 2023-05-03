package barPackage.dataAccess.utils;

import barPackage.utils.CRUDItems;
import barPackage.dataAccess.db.ToolDBAccess;
import barPackage.dataAccess.db.UnitDBAccess;

import java.util.HashMap;

public class DataConfiguration {
    private final static String DATA_PERSISTENCE = "DB"; // "DB" or "XML"
    private final static HashMap<CRUDItems, DAO> daoMap = new HashMap<>();

    public static DAO getDAO(CRUDItems item) {
        DAO readedDAO = null;
        switch (item) {
            case TOOL :
                readedDAO = daoMap.get(item);
                if (readedDAO == null) {
                    readedDAO = getToolDataAccess();
                    daoMap.put(item, readedDAO);
                }
                break;
            case UNIT :
                readedDAO = daoMap.get(item);
                if (readedDAO == null) {
                    readedDAO = getUnitDataAccess();
                    daoMap.put(item, readedDAO);
                }
                break;
            case CONSUMABLE_TYPE :
                readedDAO = daoMap.get(item);
                if (readedDAO == null) {
                    readedDAO = getConsumableTypeDataAccess();
                    daoMap.put(item, readedDAO);
                }
                break;
            case DRINK_TYPE :
                break;
            case CONSUMABLE :
                break;
            case RECIPE :
                break;
            default:
                break;
        }
        return readedDAO;
    }
    public static DAO getToolDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new ToolDBAccess();
        } else {
            return null;
        }
    }

    public static DAO getUnitDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new UnitDBAccess();
        } else {
            return null;
        }
    }

    public static DAO getConsumableTypeDataAccess() {
        if (DATA_PERSISTENCE.equals("DB")) {
            return new barPackage.dataAccess.db.ConsumableTypeDBAccess();
        } else {
            return null;
        }
    }
}
