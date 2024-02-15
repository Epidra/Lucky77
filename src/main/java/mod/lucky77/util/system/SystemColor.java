package mod.lucky77.util.system;

import net.minecraft.world.item.DyeColor;

@SuppressWarnings("unused")
public class SystemColor {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CREATE COLOR  ---------- ---------- ---------- ---------- //
	
	public static float[] createColorArray(byte color){
		int flag  = (color & 192) >> 6;
		int red   = (color &  48) >> 4;
		int green = (color &  12) >> 2;
		int blue  = (color &   3);
		return new float[]{(float)red / 255.0F, (float)green / 255.0F, (float)blue / 255.0F, flag};
	}
	
	public static float[] createColorArray(float colorID){
		if(colorID == 0) return DyeColor.YELLOW.getTextureDiffuseColors();
		if(colorID == 1) return DyeColor.RED.getTextureDiffuseColors();
		if(colorID == 2) return DyeColor.GREEN.getTextureDiffuseColors();
		if(colorID == 3) return DyeColor.BLUE.getTextureDiffuseColors();
		if(colorID == 4) return DyeColor.PURPLE.getTextureDiffuseColors();
		if(colorID == 5) return DyeColor.CYAN.getTextureDiffuseColors();
		if(colorID == 6) return DyeColor.WHITE.getTextureDiffuseColors();
		if(colorID == 7) return DyeColor.BLACK.getTextureDiffuseColors();
		return DyeColor.byId((int) colorID).getTextureDiffuseColors();
	}
	
	public static byte createColorValue(byte red, byte green, byte blue, byte flag){
		byte a = (byte) (flag  << 6);
		byte b = (byte) (red   << 4);
		byte c = (byte) (green << 2);
		byte d =         blue;
		return (byte) (a | b | c | d);
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONVERT COLOR  ---------- ---------- ---------- ---------- //
	
	public static int convert(byte[] col){
		int a = col[0]  << 16;
		int b = col[1]  <<  8;
		int c = col[2];
		return a | b | c;
	}
	
	public static byte[] convert(int col){
		int a = col >> 16;
		int b = col >>  8;
		int c = col;
		
		a = a & 255;
		b = b & 255;
		c = c & 255;
		
		return new byte[]{(byte)a, (byte)b, (byte)c};
	}
	
	
	
}
