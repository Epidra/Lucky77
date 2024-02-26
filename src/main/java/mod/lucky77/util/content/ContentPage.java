package mod.lucky77.util.content;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ContentPage {
	
	public final String pageHeader;
	public final List<String> pageBody;
	public final ResourceLocation imageSource;
	public final int imageID;
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Default Constructor **/
	public ContentPage(String pageHeader, String pageBody, String imageSource, int imageID, String modID){
		this.pageHeader  = pageHeader;
		this.pageBody    = new ArrayList<>();
		this.imageSource = new ResourceLocation(modID, imageSource);
		this.imageID     = imageID;
		this.pageBody.add(pageBody);
	}
	
	/** Second Constructor with pre-generated content **/
	public ContentPage(String pageHeader, List<String> pageBody, ResourceLocation imageSource, int imageID){
		this.pageHeader  = pageHeader;
		this.pageBody    = pageBody;
		this.imageSource = imageSource;
		this.imageID     = imageID;
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
}
