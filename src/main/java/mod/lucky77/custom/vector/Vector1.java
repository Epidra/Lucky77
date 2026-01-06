package mod.lucky77.custom.vector;

public class Vector1 {
	
	/** The Value **/
	public int X;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public Vector1(int x){
		X = x;
	}
	
	/** Constructor used to copy over another Vector **/
	public Vector1(Vector1 v){
		X = v.X;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Sets the Vector with the given value **/
	public void set(int x){
		X = x;
	}
	
	/** Sets the Vector with the value of an existing Vector **/
	public void set(Vector1 v){
		X = v.X;
	}
	
	/** Adds the given value to the Vector **/
	public void add(int x){
		X += x;
	}
	
	/** Adds the value of an existing Vector to this Vector **/
	public void add(Vector1 v){
		X += v.X;
	}
	
	/** Returns a new Vector from the value of this Vector and the given value **/
	public Vector1 offset(int x){
		return new Vector1(X + x);
	}
	
	/** Returns a new Vector from the value of this and the given Vector **/
	public Vector1 offset(Vector1 v){
		return new Vector1(X + v.X);
	}
	
	/** Checks if this Vector is identical to the given value **/
	public boolean matches(int x){
		return X == x;
	}
	
	/** Checks if this Vector is identical to the given Vector **/
	public boolean matches(Vector1 v){
		return X == v.X;
	}
	
	
	
}
