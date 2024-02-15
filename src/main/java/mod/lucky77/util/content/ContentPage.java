package mod.lucky77.util.content;

import net.minecraft.resources.ResourceLocation;

public class ContentPage {
	
	public final String pageHeader;
	public final String pageBody;
	public final ResourceLocation imageSource;
	public final int imageID;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Default Constructor **/
	public ContentPage(String pageHeader, String pageBody, String imageSource, int imageID, String modID){
		this.pageHeader  = pageHeader;
		this.pageBody    = pageBody;
		this.imageSource = new ResourceLocation(modID, imageSource);
		this.imageID     = imageID;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
}
