package mod.lucky77.common.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ItemFood extends Item {
	
	// ...
	
	
	
	
	
	//   -------- -------- -------- --------     CONSTRUCTOR     -------- -------- -------- --------   //
	
	/** Default Constructor **/
	public ItemFood(int nutrition, float saturation){
		super(new Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build()));
	}
	
	/** Constructor that handles the item the food comes in **/
	public ItemFood(int nutrition, float saturation, Item returnedItem){
		super(new Properties().stacksTo(64).food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).usingConvertsTo(returnedItem).build()));
	}
	
	
	
	
	
	//   -------- -------- -------- --------     SUPPORT     -------- -------- -------- --------   //
	
	// ...
	
	
	
}
