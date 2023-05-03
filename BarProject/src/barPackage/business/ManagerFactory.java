package barPackage.business;

import barPackage.business.interfaces.Manager;
import barPackage.utils.CRUDItems;

import java.util.HashMap;


public class ManagerFactory {
    private static final HashMap<CRUDItems, Manager> managers = new HashMap<>();
    public static Manager getManager(CRUDItems item) {
        Manager readedManager = null;
        switch (item) {
            case TOOL:
                readedManager = managers.get(CRUDItems.TOOL);
                if (readedManager == null) {
                    readedManager = new ToolManager();
                    managers.put(CRUDItems.TOOL, readedManager);
                }
                break;
            case UNIT:
                readedManager = managers.get(CRUDItems.UNIT);
                if (readedManager == null) {
                    readedManager = new UnitManager();
                    managers.put(CRUDItems.UNIT, readedManager);
                }
                break;
            case CONSUMABLE_TYPE:
                readedManager = managers.get(CRUDItems.CONSUMABLE_TYPE);
                if (readedManager == null) {
                    readedManager = new ConsumableTypeManager();
                    managers.put(CRUDItems.CONSUMABLE_TYPE, readedManager);
                }
                break;
            case DRINK_TYPE:
                break;
            case CONSUMABLE:
                break;
            case RECIPE:
                break;
            default:
                break;
        }
        return readedManager;
    }
}
