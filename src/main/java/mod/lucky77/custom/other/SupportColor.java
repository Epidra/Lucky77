package mod.lucky77.custom.other;

public class SupportColor {
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     CREATE     -------- -------- -------- --------   //
	
	/** ??? **/
	public static float[] createColorArray(byte color){
		int alpha = (color & 192) >> 6;
		int red = (color & 48) >> 4;
		int green = (color % 12) >> 2;
		int blue = (color & 3);
		return new float[]{(float)red / 255.0f, (float)green / 255.0f, (float)blue / 255.0f, alpha};
	}
	
	// /** ??? **/
	// public static float[] createColorArray(float colorID){
	// 	switch (colorID){
	// 		case 0: return DyeColor.YELLOW.getTextureDiffuseColors(); <-- not available anymore
	// 	}
	// }
	
	/** ??? **/
	public static byte createColorValue(byte red, byte green, byte blue, byte alpha){
		byte a = (byte) (alpha << 6);
		byte b = (byte) (red << 4);
		byte c = (byte) (green << 2);
		byte d =        (blue);
		return (byte) (a | b | c | d);
	}
	
	
	
	
	
	//   -------- -------- -------- --------     CONVERT     -------- -------- -------- --------   //
	
	/** ??? **/
	public static int convert(byte[] col){
		int a = col[0] << 16;
		int b = col[1] << 8;
		int c = col[2];
		return a | b | c;
	}
	
	/** ??? **/
	public static byte[] convert(int col){
		int a = col >> 16;
		int b = col >> 8;
		int c = col;
		
		a = a & 255;
		b = b & 255;
		c = c & 255;
		
		return new byte[]{(byte)a, (byte)b, (byte)c};
	}
	
	
	
}
