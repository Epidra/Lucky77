package mod.lucky77.custom.content;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record ContentPage(String pageHeader, List<String> pageBody, ResourceLocation imageSource, int imageID) {
	
}
