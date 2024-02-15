package mod.lucky77.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class ItemFood extends Item {
	
	// ...
	
	
	
	
	
	// ---------- ---------- ---------- ----------  CONSTRUCTOR  ---------- ---------- ---------- ---------- //
	
	/** Default Constructor */
	public ItemFood(int nutrition, float saturation, boolean isMeat){
		super(isMeat
				? new Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).meat().build())
				: new Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(nutrition).saturationMod(saturation).build()));
	}
	
	
	
	
	
	// ---------- ---------- ---------- ----------  SUPPORT  ---------- ---------- ---------- ---------- //
	
	// ...
	
	
	
}