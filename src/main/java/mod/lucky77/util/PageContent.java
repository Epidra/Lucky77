package mod.lucky77.util;

import net.minecraft.resources.ResourceLocation;

public class PageContent {

    public final String pageHeader;
    public final String pageBody;
    public final ResourceLocation imageSource;
    public final int imageID;





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    /** Default Constructor **/
    public PageContent(String pageHeader, String pageBody, String imageSource, int imageID, String modID){
        this.pageHeader  = pageHeader;
        this.pageBody    = pageBody;
        this.imageSource = new ResourceLocation(modID, imageSource);
        this.imageID     = imageID;
    }





    //----------------------------------------CONSTRUCTOR----------------------------------------//

    public boolean HasImage(){
        return imageID > -1;
    }



}
