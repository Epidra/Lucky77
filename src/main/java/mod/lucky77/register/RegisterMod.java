package mod.lucky77.register;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class RegisterMod {
	
	private static final Map<String, Integer> map = new HashMap<>();
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	public static void register(String id){
		map.put(id, map.size());
	}
	
	public static boolean exists(String id){
		return map.containsKey(id);
	}
	
	public static int size(){
		return map.size();
	}
	
	
	
}