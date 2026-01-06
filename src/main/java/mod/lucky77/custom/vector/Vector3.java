package mod.lucky77.custom.vector;

public class Vector3 {
	
	/** Horizontal Value **/
	public int X;
	/** Vertical Value **/
	public int Y;
	/** Front Value **/
	public int Z;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public Vector3(int x, int y, int z){
		X = x;
		Y = y;
		Z = z;
	}
	
	/** Constructor used to copy over another Vector **/
	public Vector3(Vector3 v){
		X = v.X;
		Y = v.Y;
		Z = v.Z;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Sets the Vector with the given values **/
	public void set(int x, int y, int z){
		X = x;
		Y = y;
		Z = z;
	}
	
	/** Sets the Vector with the values of an existing Vector **/
	public void set(Vector3 v){
		X = v.X;
		Y = v.Y;
		Z = v.Z;
	}
	
	/** Adds the given values to the Vector **/
	public void add(int x, int y, int z){
		X += x;
		Y += y;
		Z += z;
	}
	
	/** Adds the values of an existing Vector to this Vector **/
	public void add(Vector3 v){
		X += v.X;
		Y += v.Y;
		Z += v.Z;
	}
	
	/** Returns a new Vector from the values of this Vector and the given values **/
	public Vector3 offset(int x, int y, int z){
		return new Vector3(X + x, Y + y, Z + z);
	}
	
	/** Returns a new Vector from the values of this and the given Vector **/
	public Vector3 offset(Vector3 v){
		return new Vector3(X + v.X, Y + v.Y, Z + v.Z);
	}
	
	/** Checks if this Vector is identical to the given values **/
	public boolean matches(int x, int y, int z){
		return X == x && Y == y && Z == z;
	}
	
	/** Checks if this Vector is identical to the given Vector **/
	public boolean matches(Vector3 v){
		return X == v.X && Y == v.Y && Z == v.Z;
	}
	
	
	
}
