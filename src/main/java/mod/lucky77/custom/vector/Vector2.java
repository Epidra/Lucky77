package mod.lucky77.custom.vector;

public class Vector2 {
	
	/** Horizontal Value **/
	public int X;
	/** Vertical Value **/
	public int Y;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public Vector2(int x, int y){
		X = x;
		Y = y;
	}
	
	/** Constructor used to copy over another Vector **/
	public Vector2(Vector2 v){
		X = v.X;
		Y = v.Y;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Sets the Vector with the given values **/
	public void set(int x, int y){
		X = x;
		Y = y;
	}
	
	/** Sets the Vector with the values of an existing Vector **/
	public void set(Vector2 v){
		X = v.X;
		Y = v.Y;
	}
	
	/** Adds the given values to the Vector **/
	public void add(int x, int y){
		X += x;
		Y += y;
	}
	
	/** Adds the values of an existing Vector to this Vector **/
	public void add(Vector2 v){
		X += v.X;
		Y += v.Y;
	}
	
	/** Returns a new Vector from the values of this Vector and the given values **/
	public Vector2 offset(int x, int y){
		return new Vector2(X + x, Y + y);
	}
	
	/** Returns a new Vector from the values of this and the given Vector **/
	public Vector2 offset(Vector2 v){
		return new Vector2(X + v.X, Y + v.Y);
	}
	
	/** Checks if this Vector is identical to the given values **/
	public boolean matches(int x, int y){
		return X == x && Y == y;
	}
	
	/** Checks if this Vector is identical to the given Vector **/
	public boolean matches(Vector2 v){
		return X == v.X && Y == v.Y;
	}
	
	
	
}
