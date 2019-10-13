package database;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import service.BlogService;
import service.UserService;

public class LockManager {
	
	private Map <Integer, String> lockMapper = new ConcurrentHashMap<Integer, String>();
	private static LockManager instance;
	
	public static final LockManager getInstance() {
		if (instance == null) {
			try {
				instance = new LockManager();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	/*
	 * public static Map getLockMapper() { if (lockMapper == null) { try {
	 * lockMapper = new ConcurrentHashMap<Long, String>(); } catch (Exception e) {
	 * e.printStackTrace(); } } return lockMapper; }
	 */
	
	public boolean hasLock(Integer lockable, String owner) {
		if (lockMapper.containsKey(lockable)) {
			return lockMapper.get(lockable).equals(owner);
		}
		return false;
	}
	
	public boolean put(Integer lockable, String owner) {
		if (!lockMapper.containsKey(lockable)) {
			lockMapper.put(lockable, owner);
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean acquireLock(Integer lockable, String owner) {
		
		boolean result = true;
		
		if (!hasLock(lockable, owner)) {
			
			result = put(lockable, owner);
				
		}
		
		return result;
	}
	
	public void releaseLock(Integer lockable, String owner) {
		lockMapper.remove(lockable);
		
	}
}
