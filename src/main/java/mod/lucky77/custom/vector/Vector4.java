package mod.lucky77.custom.vector;

public class Vector4 {
	
	/** Horizontal Value **/
	public int X;
	/** Vertical Value **/
	public int Y;
	/** Front Value **/
	public int Z;
	/** Extra Value **/
	public int W;
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public Vector4(int x, int y, int z, int w){
		X = x;
		Y = y;
		Z = z;
		W = w;
	}
	
	/** Constructor used to copy over another Vector **/
	public Vector4(Vector4 v){
		X = v.X;
		Y = v.Y;
		Z = v.Z;
		W = v.W;
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	/** Sets the Vector with the given values **/
	public void set(int x, int y, int z, int w){
		X = x;
		Y = y;
		Z = z;
		W = w;
	}
	
	/** Sets the Vector with the values of an existing Vector **/
	public void set(Vector4 v){
		X = v.X;
		Y = v.Y;
		Z = v.Z;
		W = v.W;
	}
	
	/** Adds the given values to the Vector **/
	public void add(int x, int y, int z, int w){
		X += x;
		Y += y;
		Z += z;
		W += w;
	}
	
	/** Adds the values of an existing Vector to this Vector **/
	public void add(Vector4 v){
		X += v.X;
		Y += v.Y;
		Z += v.Z;
		W += v.W;
	}
	
	/** Returns a new Vector from the values of this Vector and the given values **/
	public Vector4 offset(int x, int y, int z, int w){
		return new Vector4(X + x, Y + y, Z + z, W + w);
	}
	
	/** Returns a new Vector from the values of this and the given Vector **/
	public Vector4 offset(Vector4 v){
		return new Vector4(X + v.X, Y + v.Y, Z + v.Z, W + v.W);
	}
	
	/** Checks if this Vector is identical to the given values **/
	public boolean matches(int x, int y, int z, int w){
		return X == x && Y == y && Z == z && W == w;
	}
	
	/** Checks if this Vector is identical to the given Vector **/
	public boolean matches(Vector4 v){
		return X == v.X && Y == v.Y && Z == v.Z && W == v.W;
	}
	
	
	
}
