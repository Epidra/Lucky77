package mod.lucky77.custom.content;

import java.util.HashMap;
import java.util.Map;

public class RegisterMod {
	
	private final Map<String, Integer> map = new HashMap<>();
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public RegisterMod(){
	
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Adds a new entry to the Register **/
	public void register(String id){
		map.put(id, map.size());
	}
	
	/** Checks if a specific name exists in the Register **/
	public boolean exists(String id){
		return map.containsKey(id);
	}
	
	/** Returns the size of the Register **/
	public int size(){
		return map.size();
	}
	
	
	
}
