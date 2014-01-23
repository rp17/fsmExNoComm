package west.model;
import java.util.HashMap;
import java.util.Map;

public class EntityManager {
	private static int availableID = 0;
	private Map<Integer, BaseGameEntity> entityMap = new HashMap<Integer, BaseGameEntity>();
	
	private EntityManager() {}
	private static class EntityManagerHolder {
		public static final EntityManager INSTANCE = new EntityManager();
	}
	
	public static synchronized int getAvailableID() {
		int toReturn = availableID;
		availableID++;
		return toReturn;
	}
	public static EntityManager getInstance() {
		return EntityManagerHolder.INSTANCE;
	}
	
	public static void registerEntity(BaseGameEntity entity) {
		getInstance().entityMap.put(entity.ID(), entity);
	}


	public static BaseGameEntity getEntityFromID(int receiverID) {
		return getInstance().entityMap.get(receiverID);
	}
	
	public static void removeEntity(BaseGameEntity entity) {
		getInstance().entityMap.remove(entity);
	}

	public static void reset() {
		getInstance().entityMap.clear();
		availableID = 0;
	}
}
